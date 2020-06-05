package com.kpstv.spotifyapi.data.models


import com.google.gson.annotations.SerializedName

data class Search(
    val albums: Albums?,
    val artists: Artists?,
    val episodes: Episodes?,
    val playlists: Playlists?,
    val shows: Shows?,
    val tracks: Tracks?
) {
    data class Albums(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
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
            )
        }
    }

    data class Artists(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val followers: Followers,
            val genres: List<String>,
            val href: String,
            val id: String,
            val images: List<Image>,
            val name: String,
            val popularity: Int,
            val type: String,
            val uri: String
        )
    }

    data class Episodes(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
            @SerializedName("audio_preview_url")
            val audioPreviewUrl: String,
            val description: String,
            @SerializedName("duration_ms")
            val durationMs: Int,
            val explicit: Boolean,
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val images: List<Image>,
            @SerializedName("is_externally_hosted")
            val isExternallyHosted: Boolean,
            @SerializedName("is_playable")
            val isPlayable: Boolean,
            val language: String,
            val languages: List<String>,
            val name: String,
            @SerializedName("release_date")
            val releaseDate: String,
            @SerializedName("release_date_precision")
            val releaseDatePrecision: String,
            val type: String,
            val uri: String
        )
    }

    data class Playlists(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
            val collaborative: Boolean,
            val description: String,
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val images: List<Image>,
            val name: String,
            val owner: Owner,
            @SerializedName("primary_color")
            val primaryColor: Any?,
            val `public`: Any?,
            @SerializedName("snapshot_id")
            val snapshotId: String,
            val tracks: Tracks,
            val type: String,
            val uri: String
        ) {

            data class Owner(
                @SerializedName("display_name")
                val displayName: String,
                @SerializedName("external_urls")
                val externalUrls: ExternalUrls,
                val href: String,
                val id: String,
                val type: String,
                val uri: String
            )

            data class Tracks(
                val href: String,
                val total: Int
            )
        }
    }

    data class Shows(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
            @SerializedName("available_markets")
            val availableMarkets: List<String>,
            val copyrights: List<Any>,
            val description: String,
            val explicit: Boolean,
            @SerializedName("external_urls")
            val externalUrls: ExternalUrls,
            val href: String,
            val id: String,
            val images: List<Image>,
            @SerializedName("is_externally_hosted")
            val isExternallyHosted: Boolean,
            val languages: List<String>,
            @SerializedName("media_type")
            val mediaType: String,
            val name: String,
            val publisher: String,
            val type: String,
            val uri: String
        )
    }

    data class Tracks(
        val href: String,
        val items: List<Item>,
        val limit: Int,
        val next: String,
        val offset: Int,
        val previous: Any?,
        val total: Int
    ) {
        data class Item(
            val album: Album,
            val artists: List<Artist>,
            @SerializedName("available_markets")
            val availableMarkets: List<String>,
            @SerializedName("disc_number")
            val discNumber: Int,
            @SerializedName("duration_ms")
            val durationMs: Int,
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
            )
        }
    }
}