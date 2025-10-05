package com.scholarservice.controller;

import com.scholarservice.model.Article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Manages all database operations for the application.
 * This class handles the connection to the MySQL database and the insertion of data.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class DatabaseManager {
    /** The JDBC connection URL for the local MySQL database. */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/scholar_db";
    /** The username for the database connection. */
    private static final String USER = "root";
    /** The password for the database connection. */
    private static final String PASS = "123456";

    /**
     * Saves a list of Article objects to the 'articles' table in the database.
     * It establishes a connection, prepares a SQL INSERT statement, and executes it for each article.
     *
     * @param articles A List of Article objects to be saved.
     */
    public void saveArticles(List<Article> articles) {
        String sql = "INSERT INTO articles (title, authors, publication_info, snippet, link, cited_by, researcher_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Use a try-with-resources statement to ensure the connection is automatically closed.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("\nConnected to the database. Saving articles...");

            for (Article article : articles) {
                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, article.getAuthors());
                pstmt.setString(3, article.getPublicationInfo());
                pstmt.setString(4, article.getSnippet());
                pstmt.setString(5, article.getLink());
                pstmt.setInt(6, article.getCitedBy() != null ? article.getCitedBy().getValue() : 0);
                pstmt.setString(7, article.getResearcherId());
                pstmt.executeUpdate(); // Execute the insert statement
            }

            System.out.println(articles.size() + " articles saved successfully to the database!");

        } catch (SQLException e) {
            // Handle any potential database errors.
            System.err.println("Error saving to the database: " + e.getMessage());
        }
    }
}