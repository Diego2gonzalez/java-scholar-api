package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models the "citations" object within the API response,
 * which contains the total citation count.
 */
public class CitationsInfo {

    @SerializedName("all")
    private int all;

    /**
     * Gets the total number of citations.
     * @return The total citation count.
     */
    public int getAll() {
        return all;
    }
}