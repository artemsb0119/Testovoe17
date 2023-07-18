package com.example.testovoe17

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Round(

    @SerializedName("data") val data : String,
    @SerializedName("firma") val firma : String,
    @SerializedName("avatar") val avatar : String,
    @SerializedName("title") val title : String,
    @SerializedName("raised") val raised : String,
    @SerializedName("raised_result") val raised_result : String,
    @SerializedName("investors") val investors : String
) : Serializable
