package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

public class PublicationResult {
    @SerializedName("publication_info")
    private PublicationInfo publicationInfo;

    public PublicationInfo getPublicationInfo() {
        return publicationInfo;
    }
}