package com.scholarservice.model;

import com.google.gson.annotations.SerializedName;

public class AuthorInfo {
    @SerializedName("name")
    private String name;

    @SerializedName("author_id")
    private String authorId;

    public String getName() {
        return name;
    }

    public String getAuthorId() {
        return authorId;
    }
}