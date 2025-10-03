package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models the entire JSON response for a detailed author profile search.
 */
public class AuthorProfileResponse {
    @SerializedName("author")
    private AuthorProfile author;

    @SerializedName("cited_by")
    private CitedBy citedBy;

    /**
     * Gets the author profile object.
     * @return The AuthorProfile object.
     */
    public AuthorProfile getAuthor() {
        return author;
    }

    /**
     * Gets the citation data object.
     * @return The CitedBy object.
     */
    public CitedBy getCitedBy() {
        return citedBy;
    }
}