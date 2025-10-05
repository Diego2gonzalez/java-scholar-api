package com.scholarservice.controller;

import com.google.gson.Gson;
import com.scholarservice.model.*;
import com.scholarservice.view.AuthorView;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The Controller component in the MVC pattern.
 * It handles the application's core logic, including making API requests,
 * processing data, and updating the view.
 */
public class AuthorController {
    private final AuthorView view;
    private final String apiKey = "a8fc5bcaa26cc72edfb3ab2e005ed622006d635bcd6123195cfcc5677a536a2d";
    private final Gson gson = new Gson();
    private final DatabaseManager dbManager = new DatabaseManager();

    /**
     * Constructor for the AuthorController.
     * @param view The view instance to be updated with data.
     */
    public AuthorController(AuthorView view) {
        this.view = view;
    }

    /**
     * Searches for authors by name using the Google Scholar API.
     * It processes the results to create a list of unique authors and their IDs.
     * @param authorName The name of the author to search for.
     */
    public void findAuthorsByName(String authorName) {
        try {
            String encodedName = URLEncoder.encode(authorName, StandardCharsets.UTF_8.toString());
            String url = String.format("https://serpapi.com/search.json?engine=google_scholar&q=%s&api_key=%s", encodedName, apiKey);

            String jsonResponse = executeGetRequest(url);
            if (jsonResponse == null) return;

            ScholarResponse scholarResponse = gson.fromJson(jsonResponse, ScholarResponse.class);
            Map<String, AuthorInfo> uniqueAuthors = new LinkedHashMap<>();

            if (scholarResponse.getOrganicResults() != null) {
                for (PublicationResult result : scholarResponse.getOrganicResults()) {
                    if (result.getPublicationInfo() != null && result.getPublicationInfo().getAuthors() != null) {
                        for (AuthorInfo author : result.getPublicationInfo().getAuthors()) {
                            if (author.getAuthorId() != null && !author.getAuthorId().isEmpty()) {
                                uniqueAuthors.putIfAbsent(author.getAuthorId(), author);
                            }
                        }
                    }
                }
            }
            view.displayAuthorList(uniqueAuthors);

        } catch (Exception e) {
            view.displayError("An error occurred during the name search: " + e.getMessage());
        }
    }

    /**
     * Fetches and displays the detailed profile of an author using their unique Google Scholar ID.
     * This method only displays the data and does not save it.
     * @param authorId The unique ID of the author.
     * @return true if articles were found, otherwise false.
     */
    public boolean findDetailsByAuthorId(String authorId) {
        try {
            String encodedId = URLEncoder.encode(authorId, StandardCharsets.UTF_8.toString());
            String url = String.format("https://serpapi.com/search.json?engine=google_scholar_author&author_id=%s&api_key=%s", encodedId, apiKey);
            String jsonResponse = executeGetRequest(url);
            if (jsonResponse == null) return false;

            AuthorProfileResponse profileResponse = gson.fromJson(jsonResponse, AuthorProfileResponse.class);
            if (profileResponse != null && profileResponse.getAuthor() != null) {
                view.displayAuthorProfile(profileResponse);

                // Return true if articles are present and available to be saved
                return profileResponse.getArticles() != null && !profileResponse.getArticles().isEmpty();
            } else {
                view.displayError("Could not retrieve the profile for the provided ID.");
                return false;
            }
        } catch (Exception e) {
            view.displayError("An error occurred during the ID search: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetches author data again and saves their top 3 articles to the database.
     * This method is called only after the user confirms they want to save the data.
     * @param authorId The unique ID of the author whose articles will be saved.
     */
    public void saveArticlesToDatabase(String authorId) {
        try {
            // We repeat the API call to get the data again before saving.
            // A more advanced implementation might cache the previous response, but this is simpler and effective.
            String encodedId = URLEncoder.encode(authorId, StandardCharsets.UTF_8.toString());
            String url = String.format("https://serpapi.com/search.json?engine=google_scholar_author&author_id=%s&api_key=%s", encodedId, apiKey);
            String jsonResponse = executeGetRequest(url);
            if (jsonResponse == null) return;

            AuthorProfileResponse profileResponse = gson.fromJson(jsonResponse, AuthorProfileResponse.class);
            if (profileResponse != null && profileResponse.getArticles() != null && !profileResponse.getArticles().isEmpty()) {
                List<Article> articlesToSave = profileResponse.getArticles().subList(0, Math.min(3, profileResponse.getArticles().size()));

                for(Article article : articlesToSave) {
                    article.setResearcherId(authorId);
                }

                dbManager.saveArticles(articlesToSave);
            }
        } catch (Exception e) {
            view.displayError("An error occurred while trying to save the articles: " + e.getMessage());
        }
    }

    /**
     * A private helper method to execute an HTTP GET request.
     * @param url The URL to send the GET request to.
     * @return A string containing the JSON response, or null if an error occurred.
     */
    private String executeGetRequest(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                if (response.getStatusLine().getStatusCode() == 200) {
                    return jsonResponse;
                } else {
                    view.displayError("API returned an error. Code: " + response.getStatusLine().getStatusCode() + ". Response: " + jsonResponse);
                    return null;
                }
            }
        } catch (Exception e) {
            view.displayError("HTTP request failed: " + e.getMessage());
        }
        return null;
    }
}