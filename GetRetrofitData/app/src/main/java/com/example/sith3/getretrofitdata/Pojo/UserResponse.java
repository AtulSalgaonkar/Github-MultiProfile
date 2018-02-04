package com.example.sith3.getretrofitdata.Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 2/3/2018.
 */

public class UserResponse {

    @SerializedName("login")
    String username;

    @SerializedName("avatar_url")
    String profilePicUrl;

    @SerializedName("html_url")
    String profileUrl;

    public UserResponse(String username, String profilePicUrl, String profileUrl) {
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.profileUrl = profileUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
