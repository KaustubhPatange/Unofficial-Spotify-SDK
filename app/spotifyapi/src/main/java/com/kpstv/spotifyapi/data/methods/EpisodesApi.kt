package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Episode
import com.kpstv.spotifyapi.data.models.Episodes

class EpisodesApi(
    private val client: SpotifyClient
) {
    /**
     * Get Spotify catalog information for a single episode identified by
     * its unique Spotify ID.
     */
    fun getEpisode(id: String, responseAction: ResponseAction<Episode>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/episodes/${id}",
                type = Episode::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information for several episodes based on their Spotify IDs.
     */
    fun getMultipleEpisodes(ids: Array<String>, responseAction: ResponseAction<Episodes>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/episodes?ids=${ids.joinToString(",")}",
                type = Episodes::class.java,
                responseAction = responseAction
            )
        }
    }
}