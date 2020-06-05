package com.kpstv.spotifyapi.data.models

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    val copyrights: List<Any>,
    val description: String,
    val episodes: ShowEpisodes?,
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