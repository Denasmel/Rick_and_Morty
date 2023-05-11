package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class ServerData(
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)