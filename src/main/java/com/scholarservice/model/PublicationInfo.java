package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PublicationInfo {
    @SerializedName("authors")
    private List<AuthorInfo> authors;

    public List<AuthorInfo> getAuthors() {
        return authors;
    }
}