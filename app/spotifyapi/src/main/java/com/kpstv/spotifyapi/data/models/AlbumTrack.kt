package com.kpstv.spotifyapi.data.models


import com.google.gson.annotations.SerializedName

data class AlbumTrack(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any?,
    val total: Int
)