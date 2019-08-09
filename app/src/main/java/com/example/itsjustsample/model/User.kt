package com.example.githubapisample.model

import android.os.Parcelable
import com.example.itsjustsample.model.ListItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class User(
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("followers_url")
    val followers_url: String,
    @SerializedName("following_url")
    val following_url: String,
    @SerializedName("gists_url")
    val gists_url: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val node_id: String,
    @SerializedName("repos_url")
    val repos_url: String,
    @SerializedName("starred_url")
    val starred_url: String
): ListItem()