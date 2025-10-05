package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Models the "publication_info" object found within a single search result.
 * Its primary purpose is to hold the list of authors associated with that publication.
 *
 * @author Diego González Miranda
 * @version 1.0
 */
public class PublicationInfo {
    @SerializedName("authors")
    private List<AuthorInfo> authors;

    /**
     * Gets the list of authors for the publication.
     * @return A List of AuthorInfo objects.
     */
    public List<AuthorInfo> getAuthors() {
        return authors;
    }
}