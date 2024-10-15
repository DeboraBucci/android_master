package com.example.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/3d31afd2c10e2e0fa4cf8d6c59b9b5d0/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String) : Response<SuperHeroDataResponse>
}