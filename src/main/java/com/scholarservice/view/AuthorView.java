package com.scholarservice.view;

import com.scholarservice.model.*;
import java.util.List; // <-- ¡Esta es la línea que faltaba!
import java.util.Map;

/**
 * The View component in the MVC pattern.
 * This class is responsible for displaying all information to the user in the console.
 */
public class AuthorView {

    /**
     * Displays a list of authors found from a name search.
     * @param authors A map containing the author's ID as the key and AuthorInfo as the value.
     */
    public void displayAuthorList(Map<String, AuthorInfo> authors) {
        if (authors.isEmpty()) {
            System.out.println("No unique authors were found with that name.");
            return;
        }
        System.out.println("\n--- The following authors were found ---");
        System.out.println("Please copy and paste the ID of the author you are interested in:");
        authors.forEach((id, author) -> {
            System.out.println("ID: " + id + " | Name: " + author.getName());
        });
        System.out.println("-------------------------------------------");
    }

    /**
     * Displays the detailed profile of a specific author, followed by their top 3 articles.
     * @param response The complete API response object containing the profile and citation data.
     */
    public void displayAuthorProfile(AuthorProfileResponse response) {
        AuthorProfile profile = response.getAuthor();
        CitedBy citedByData = response.getCitedBy();

        // Muestra la información del perfil del autor
        System.out.println("\n--- Author's Detailed Profile ---");
        System.out.println("Name: " + profile.getName());
        System.out.println("Affiliation: " + (profile.getAffiliations() != null ? profile.getAffiliations() : "Not available"));
        System.out.println("Email: " + (profile.getEmail() != null ? profile.getEmail() : "Not available"));

        try {
            if (citedByData != null && citedByData.getTable() != null && !citedByData.getTable().isEmpty()) {
                int totalCitations = citedByData.getTable().get(0).getCitations().getAll();
                System.out.println("Total Citations: " + totalCitations);
            }
        } catch (Exception e) {
            // If any part of the structure is null, the citation line is simply not printed.
        }
        System.out.println("-----------------------------------");

        // Muestra los 3 primeros artículos del autor
        if (response.getArticles() != null && !response.getArticles().isEmpty()) {
            System.out.println("\n--- Top 3 Articles Found ---");
            List<Article> topArticles = response.getArticles().subList(0, Math.min(3, response.getArticles().size()));

            int articleNumber = 1;
            for (Article article : topArticles) {
                System.out.println("\n" + articleNumber + ". Title: " + article.getTitle());
                System.out.println("   Authors: " + article.getAuthors());
                System.out.println("   Publication: " + article.getPublicationInfo());
                if (article.getCitedBy() != null) {
                    System.out.println("   Cited by: " + article.getCitedBy().getValue());
                }
                articleNumber++;
            }
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Displays an error message to the console.
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }
}