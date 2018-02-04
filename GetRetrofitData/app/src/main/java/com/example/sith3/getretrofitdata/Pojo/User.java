package com.example.sith3.getretrofitdata.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created on 2/3/2018.
 */

public class User {

    @SerializedName("total_count")
    String totalCount;

    @SerializedName("incomplete_results")
    String incompleteResults;

    @SerializedName("items")
    ArrayList<UserResponse> items;

    public User(String totalCount, String incompleteResults, ArrayList<UserResponse> items) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public String getIncompleteResults() {
        return incompleteResults;
    }

    public ArrayList<UserResponse> getItems() {
        return items;
    }
}
