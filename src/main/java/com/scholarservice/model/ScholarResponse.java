package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ScholarResponse {
    @SerializedName("organic_results")
    private List<PublicationResult> organicResults;

    public List<PublicationResult> getOrganicResults() {
        return organicResults;
    }
}