package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models an entry in the "table" array of the "cited_by" object,
 * containing citation details.
 */
public class TableEntry {

    @SerializedName("citations")
    private CitationsInfo citations;

    /**
     * Gets the citation information object.
     * @return The CitationsInfo object.
     */
    public CitationsInfo getCitations() {
        return citations;
    }
}