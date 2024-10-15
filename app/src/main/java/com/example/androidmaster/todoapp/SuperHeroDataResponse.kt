package com.example.androidmaster.todoapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)