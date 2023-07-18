package com.example.testovoe17

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Crypto(

    @SerializedName("id") val id : Int,
    @SerializedName("image") val image : String,
    @SerializedName("name") val name : String,
    @SerializedName("title") val title : String,
    @SerializedName("cost") val cost : String,
    @SerializedName("proc") val proc : String,
    @SerializedName("graf") val graf : String,
    @SerializedName("market") val market : String,
    @SerializedName("supply") val supply : String,
    @SerializedName("total") val total : String,
    @SerializedName("fully") val fully : String,
    @SerializedName("volume") val volume : String,
    @SerializedName("all") val all : String,
    @SerializedName("data") val data : String,
    @SerializedName("proc1") val proc1 : String
) : Serializable
