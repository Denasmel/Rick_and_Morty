package com.example.rickandmorty.data.cloud

import com.example.rickandmorty.data.model.ServerData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RickAndMortyApi {

    @Headers("Content-Type: application/json")
    @GET("character")
    suspend fun getRickAndMortyServerData(@Query("page") page: Int): ServerData

}