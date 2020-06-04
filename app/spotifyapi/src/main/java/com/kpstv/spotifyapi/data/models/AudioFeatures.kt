package com.kpstv.spotifyapi.data.models


import com.google.gson.annotations.SerializedName

data class AudioFeatures(
    @SerializedName("audio_features")
    val audioFeatures: List<AudioFeature>
)