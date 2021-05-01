package com.example.dindinapp.models
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Created by Gbenga Oladipupo(Devmike01) on 5/1/21.

public class StarredResponse {

    @SerializedName("id")
    @Expose
    var id : Int =0;
    @SerializedName("node_id")
    @Expose
    var nodeId : String? = null;
    @SerializedName("name")
    @Expose
    var name:  String? =null;
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null;
    @SerializedName("private")
    @Expose
    var isPrivate :  Boolean = false;
    @SerializedName("html_url")
    @Expose
    var htmlUrl:  String? = null;
    @SerializedName("description")
    @Expose
    var description: String? = null;
    @SerializedName("fork")
    @Expose
    var fork: Boolean? = false;
    @SerializedName("languages_url")
    @Expose
    var languagesUrl : String? = null
    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount : Int? =0;
    @SerializedName("watchers_count")
    @Expose
    var watchersCount : Int =0;
    @SerializedName("pushed_at")
    @Expose
    var pushedAt : String? = null;
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null;
    @SerializedName("updated_at")
    @Expose
    var updatedAt : String? = null;
    @SerializedName("subscribers_count")
    @Expose
    var subscribersCount: Int = 0;

}