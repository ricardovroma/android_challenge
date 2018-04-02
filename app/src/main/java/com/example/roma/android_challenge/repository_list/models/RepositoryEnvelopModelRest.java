package com.example.roma.android_challenge.repository_list.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositoryEnvelopModelRest implements Serializable {

    @SerializedName("total_count")
    public Integer totalCount;

    public ArrayList<RepositoryItemModelRest> items;

}
