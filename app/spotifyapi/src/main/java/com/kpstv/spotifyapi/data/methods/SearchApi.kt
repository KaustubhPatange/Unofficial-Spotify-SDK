package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Search
import com.kpstv.spotifyapi.enumerations.Type
import java.net.URLEncoder

class SearchApi(
    private val client: SpotifyClient
) {
    /**
     * Get Spotify Catalog information about albums, artists, playlists, tracks,
     * shows or episodes that match a keyword string.
     */
    fun searchItem(
        query: String,
        types: Array<Type>,
        limit: Int = 10,
        offset: Int = 5,
        responseAction: ResponseAction<Search>
    ) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeClient(
                url = "https://api.spotify.com/v1/search?q=${URLEncoder.encode(
                    query,
                    "UTF-8"
                )}&limit=${limit}&offset=${offset}&type=${types.joinToString(",") { it.type }}",
                type = Search::class.java,
                responseAction = responseAction
            )
        }
    }
}