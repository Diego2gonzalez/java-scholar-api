package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Models the entire JSON response for an initial author name search.
 * This class acts as the top-level container for the list of organic search results,
 * which are the publications found.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class ScholarResponse {
    @SerializedName("organic_results")
    private List<PublicationResult> organicResults;

    /**
     * Gets the list of organic search results (publications).
     * @return A List of PublicationResult objects.
     */
    public List<PublicationResult> getOrganicResults() {
        return organicResults;
    }
}