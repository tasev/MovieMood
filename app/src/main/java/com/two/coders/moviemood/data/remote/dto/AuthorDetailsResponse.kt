package com.two.coders.moviemood.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthorDetailsResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar_path")
    val avatarPath: String? = null,
    @SerializedName("rating")
    val rating: String
)