package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Models the entire JSON response received when searching for a specific author by their ID.
 * This class acts as the top-level container for the author's profile,
 * their citation data, and their list of articles.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class AuthorProfileResponse {
    @SerializedName("author")
    private AuthorProfile author;

    @SerializedName("cited_by")
    private CitedBy citedBy;

    @SerializedName("articles")
    private List<Article> articles;

    /**
     * Gets the author's detailed profile object.
     * @return The AuthorProfile object containing the author's information.
     */
    public AuthorProfile getAuthor() { return author; }

    /**
     * Gets the citation data object.
     * @return The CitedBy object containing citation metrics.
     */
    public CitedBy getCitedBy() { return citedBy; }

    /**
     * Gets the list of articles written by the author.
     * @return A List of Article objects.
     */
    public List<Article> getArticles() { return articles; }
}