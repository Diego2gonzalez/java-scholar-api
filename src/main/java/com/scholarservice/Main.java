package com.scholarservice;

import com.scholarservice.controller.AuthorController;
import com.scholarservice.view.AuthorView;
import java.util.Scanner;

/**
 * The main entry point for the Google Scholar Author Search application.
 * This class initializes the MVC components and handles the user interaction loop,
 * guiding the user through the search and save process.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class Main {
    /**
     * The main method that runs the application. It orchestrates the entire user workflow.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize the MVC components
        AuthorView view = new AuthorView();
        AuthorController controller = new AuthorController(view);
        Scanner scanner = new Scanner(System.in);

        // --- STEP 1: Search by name ---
        System.out.print("Enter the author's name to search: ");
        String authorName = scanner.nextLine();

        if (authorName != null && !authorName.trim().isEmpty()) {
            controller.findAuthorsByName(authorName);

            // --- STEP 2: Prompt for an ID to get details ---
            System.out.print("\nEnter the ID of the author to see their details (or type 'exit'): ");
            String authorId = scanner.nextLine();

            if (authorId != null && !authorId.trim().isEmpty() && !authorId.equalsIgnoreCase("exit")) {
                // The controller will display the details and tell us if articles were found
                boolean articlesFound = controller.findDetailsByAuthorId(authorId);

                // --- NEW LOGIC TO ASK FOR CONFIRMATION ---
                if (articlesFound) {
                    System.out.print("\nDo you want to save the top 3 articles to the database? (yes/no): ");
                    String userResponse = scanner.nextLine();

                    if (userResponse.equalsIgnoreCase("yes")) {
                        controller.saveArticlesToDatabase(authorId);
                    } else {
                        System.out.println("Articles were not saved.");
                    }
                }
            } else {
                System.out.println("Search finished.");
            }
        } else {
            System.out.println("The author's name cannot be empty.");
        }

        scanner.close();
    }
}