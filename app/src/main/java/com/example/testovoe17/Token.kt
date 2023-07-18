package com.example.testovoe17

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Token(

    @SerializedName("data") val data : String,
    @SerializedName("firma") val firma : String,
    @SerializedName("avatar") val avatar : String,
    @SerializedName("title") val title : String,
    @SerializedName("text") val text : String,
    @SerializedName("raise") val raise : String,
    @SerializedName("raise_result") val raise_result : String,
    @SerializedName("category") val category : String,
    @SerializedName("category_result") val category_result : String
) : Serializable
