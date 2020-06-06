package com.kpstv.spotifyapi.data.models


import com.google.gson.annotations.SerializedName

data class Playlist(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Any?,
    val offset: Int,
    val previous: Any?,
    val total: Int
) {
    data class Item(
        @SerializedName("added_at")
        val addedAt: String,
        @SerializedName("added_by")
        val addedBy: AddedBy,
        @SerializedName("is_local")
        val isLocal: Boolean,
        @SerializedName("primary_color")
        val primaryColor: Any?,
        val track: Track,
        @SerializedName("video_thumbnail")
        val videoThumbnail: VideoThumbnail
    ) {
        data class AddedBy(
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val type: String,
            val uri: String
        ) {
            data class ExternalUrls(
                val spotify: String
            )
        }

        data class Track(
            val album: Album,
            val artists: List<Artist>,
            @SerializedName("available_markets")
            val availableMarkets: List<String>,
            @SerializedName("disc_number")
            val discNumber: Int,
            @SerializedName("duration_ms")
            val durationMs: Int,
            val episode: Boolean,
            val explicit: Boolean,
            @SerializedName("external_ids")
            val externalIds: ExternalIds,
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            @SerializedName("is_local")
            val isLocal: Boolean,
            val name: String,
            val popularity: Int,
            @SerializedName("preview_url")
            val previewUrl: Any?,
            val track: Boolean,
            @SerializedName("track_number")
            val trackNumber: Int,
            val type: String,
            val uri: String
        ) {
            data class Album(
                @SerializedName("album_type")
                val albumType: String,
                val artists: List<Artist>,
                @SerializedName("available_markets")
                val availableMarkets: List<String>,
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

            data class ExternalIds(
                val isrc: String
            )

            data class ExternalUrls(
                val spotify: String
            )
        }

        data class VideoThumbnail(
            val url: Any?
        )
    }
}