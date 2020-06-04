package com.kpstv.spotifyapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    @Expose
    val accessToken: String,

    @SerializedName("refresh_token")
    @Expose
    val refreshToken: String?,

    @SerializedName("token_type")
    @Expose
    val tokenType: String?
)