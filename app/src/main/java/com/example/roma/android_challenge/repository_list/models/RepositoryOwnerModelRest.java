package com.example.roma.android_challenge.repository_list.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepositoryOwnerModelRest implements Serializable {
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;
}
