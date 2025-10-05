package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models a basic author object, typically found within a list of publications.
 * This class holds the author's name and their unique Google Scholar ID,
 * used in the first step of the search process.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class AuthorInfo {
    @SerializedName("name")
    private String name;

    @SerializedName("author_id")
    private String authorId;

    /**
     * Gets the author's name.
     * @return The name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the author's unique Google Scholar ID.
     * @return The author's ID string.
     */
    public String getAuthorId() {
        return authorId;
    }
}