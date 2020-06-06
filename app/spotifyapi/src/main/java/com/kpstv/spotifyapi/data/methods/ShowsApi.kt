package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Show
import com.kpstv.spotifyapi.data.models.ShowEpisodes
import com.kpstv.spotifyapi.data.models.Shows

class ShowsApi(
    private val client: SpotifyClient
){
    /**
     * Get Spotify catalog information for a single show identified by its unique Spotify ID.
     */
    fun getShow(id: String, responseAction: ResponseAction<Show>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/shows/${id}",
                type = Show::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information for several shows based on their Spotify IDs.
     */
    fun getMultipleShows(ids: Array<String>, responseAction: ResponseAction<Shows>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/shows?ids=${ids.joinToString(",")}",
                type = Shows::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get a Show's Episodes
     */
    fun getShowEpisodes(id: String, limit: Int = 10, offset: Int = 5, responseAction: ResponseAction<ShowEpisodes>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/shows/${id}/episodes?limit=${limit}&offset=${offset}",
                type = ShowEpisodes::class.java,
                responseAction = responseAction
            )
        }
    }
}