package com.kpstv.spotifyapi.data.models


import com.google.gson.annotations.SerializedName

data class ArtistAlbum(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
) {
    data class Item(
        @SerializedName("album_group")
        val albumGroup: String,
        @SerializedName("album_type")
        val albumType: String,
        val artists: List<Artist>,
        @SerializedName("external_urls")
        val externalUrls: ExternalUrls,
        val href: String,
        val id: String,
        val images: List<Image>,
        val name: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("release_date_precision")
        val releaseDatePrecision: String,
        @SerializedName("total_tracks")
        val totalTracks: Int,
        val type: String,
        val uri: String
    ) {
        data class Artist(
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val name: String,
            val type: String,
            val uri: String
        ) {
            data class ExternalUrls(
                val spotify: String
            )
        }

        data class ExternalUrls(
            val spotify: String
        )

        data class Image(
            val height: Int,
            val url: String,
            val width: Int
        )
    }
}