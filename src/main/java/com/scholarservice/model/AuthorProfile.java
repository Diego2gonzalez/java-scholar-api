package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models the detailed "author" object containing profile information.
 */
public class AuthorProfile {
    @SerializedName("name")
    private String name;

    @SerializedName("affiliations")
    private String affiliations;

    @SerializedName("email")
    private String email;

    /**
     * Gets the author's name.
     * @return The name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the author's affiliations.
     * @return The affiliations of the author.
     */
    public String getAffiliations() {
        return affiliations;
    }

    /**
     * Gets the author's email.
     * @return The email of the author.
     */
    public String getEmail() {
        return email;
    }
}