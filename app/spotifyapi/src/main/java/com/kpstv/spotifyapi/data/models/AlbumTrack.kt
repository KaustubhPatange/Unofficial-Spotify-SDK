package com.kpstv.spotifyapi.data.models


data class AlbumTrack(
    val href: String,
    val items: List<AlbumItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any?,
    val total: Int
)