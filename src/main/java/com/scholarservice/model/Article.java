package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models a single article from the Google Scholar API response.
 * This class is used by Gson to parse the JSON data for each article.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class Article {

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("authors")
    private String authors;

    @SerializedName("publication")
    private String publicationInfo;

    @SerializedName("snippet")
    private String snippet;

    @SerializedName("cited_by")
    private CitedByInfo citedBy;

    /** This field is not from the API; it's set manually to link the article to a researcher. */
    private String researcherId;

    // --- Getters and Setters ---

    /**
     * Gets the title of the article.
     * @return The article's title.
     */
    public String getTitle() { return title; }

    /**
     * Gets the direct link to the article on Google Scholar.
     * @return The URL of the article.
     */
    public String getLink() { return link; }

    /**
     * Gets the list of authors for the article as a single string.
     * @return The authors' names.
     */
    public String getAuthors() { return authors; }

    /**
     * Gets the publication information (e.g., journal, year).
     * @return The publication info string.
     */
    public String getPublicationInfo() { return publicationInfo; }

    /**
     * Gets the brief summary or abstract of the article.
     * @return The article snippet.
     */
    public String getSnippet() { return snippet; }

    /**
     * Gets the citation information for the article.
     * @return The CitedByInfo object.
     */
    public CitedByInfo getCitedBy() { return citedBy; }

    /**
     * Gets the ID of the researcher this article belongs to.
     * @return The researcher's unique ID.
     */
    public String getResearcherId() { return researcherId; }

    /**
     * Sets the ID of the researcher for this article.
     * @param researcherId The researcher's unique ID.
     */
    public void setResearcherId(String researcherId) { this.researcherId = researcherId; }

    /**
     * A nested static class to model the "cited_by" object within an article's JSON data.
     */
    public static class CitedByInfo {
        @SerializedName("value")
        private int value = 0;

        /**
         * Gets the number of citations for this specific article.
         * @return The citation count.
         */
        public int getValue() { return value; }
    }
}