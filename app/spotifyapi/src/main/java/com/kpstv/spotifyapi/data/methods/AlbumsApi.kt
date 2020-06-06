package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Album
import com.kpstv.spotifyapi.data.models.AlbumTrack
import com.kpstv.spotifyapi.data.models.Albums

class AlbumsApi(
    private val client: SpotifyClient
) {

    /**
     * Get Several Albums
     */
    fun getSeveralAlbums(ids: Array<String>, responseAction: ResponseAction<Albums>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/albums?ids=${ids.joinToString(",")}",
                type = Albums::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information for a single album.
     */
    fun getAlbum(id: String, responseAction: ResponseAction<Album>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/albums/${id}",
                type = Album::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information about an album’s tracks.
     * Optional parameters can be used to limit the number of tracks returned.
     */
    fun getAlbumTracks(id: String, limit: Int = 10, offset: Int = 5, responseAction: ResponseAction<AlbumTrack>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/albums/${id}/tracks?limit=${limit}&offset=${offset}",
                type = AlbumTrack::class.java,
                responseAction = responseAction
            )
        }
    }
}