package com.scholarservice;

import com.scholarservice.controller.AuthorController;
import com.scholarservice.view.AuthorView;
import java.util.Scanner;

/**
 * The main entry point for the Google Scholar Author Search application.
 * This class initializes the MVC components and handles the user interaction loop.
 *
 * @author [Diego]
 * @version 1.0
 */
public class Main {
    /**
     * The main method that runs the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the MVC components
        AuthorView view = new AuthorView();
        AuthorController controller = new AuthorController(view);
        Scanner scanner = new Scanner(System.in);

        // --- Search by name ---
        System.out.print("Enter the author's name to search: ");
        String authorName = scanner.nextLine();

        if (authorName != null && !authorName.trim().isEmpty()) {
            controller.findAuthorsByName(authorName);

            // --- Prompt for an ID and search for details ---
            System.out.print("\nEnter the ID of the author to see their details (or type 'exit'): ");
            String authorId = scanner.nextLine();

            if (authorId != null && !authorId.trim().isEmpty() && !authorId.equalsIgnoreCase("exit")) {
                controller.findDetailsByAuthorId(authorId);
            } else {
                System.out.println("Search finished.");
            }
        } else {
            System.out.println("The author's name cannot be empty.");
        }

        scanner.close();
    }
}