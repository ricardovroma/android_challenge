package com.example.roma.android_challenge.repository_list.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepositoryItemModelRest implements Serializable {
    public String name;

    @SerializedName("full_name")
    public String fullName;

    public String description;
    public RepositoryOwnerModelRest owner;

    @SerializedName("forks_count")
    public Integer forksCount;

    @SerializedName("stargazers_count")
    public Integer stargazersCount;

    @SerializedName("html_url")
    public String htmlUrl;


}
