package com.kpstv.spotifyapi.data.models

data class ShowEpisodes(
    val href: String,
    val items: List<ShowItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any?,
    val total: Int
)