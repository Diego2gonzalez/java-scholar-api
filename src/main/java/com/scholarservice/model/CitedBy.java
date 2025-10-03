package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Models the top-level "cited_by" object from the author profile API response.
 */
public class CitedBy {

    @SerializedName("table")
    private List<TableEntry> table;

    /**
     * Gets the list of table entries containing citation metrics.
     * @return A list of TableEntry objects.
     */
    public List<TableEntry> getTable() {
        return table;
    }
}