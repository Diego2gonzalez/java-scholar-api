package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

/**
 * Models a single publication result (an "organic_result") from the initial name search.
 * It acts as a container for the publication's details, such as the author list.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class PublicationResult {
    @SerializedName("publication_info")
    private PublicationInfo publicationInfo;

    /**
     * Gets the publication information object.
     * @return The PublicationInfo object, which contains details like the author list.
     */
    public PublicationInfo getPublicationInfo() {
        return publicationInfo;
    }
}