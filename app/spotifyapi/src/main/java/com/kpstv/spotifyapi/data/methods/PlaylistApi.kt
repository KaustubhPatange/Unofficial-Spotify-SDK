package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Playlist

class PlaylistApi(
    private val client: SpotifyClient
) {
    /**
     * Get a Playlist's Items
     */
    fun getPlaylistItems(id: String, limit: Int = 10, offset: Int = 5, responseAction: ResponseAction<Playlist>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/playlists/${id}/tracks?limit=${limit}&offset=${offset}",
                type = Playlist::class.java,
                responseAction = responseAction
            )
        }
    }
}