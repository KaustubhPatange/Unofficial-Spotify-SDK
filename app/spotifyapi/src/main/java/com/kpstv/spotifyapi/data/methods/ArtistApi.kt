package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Artist
import com.kpstv.spotifyapi.data.models.ArtistAlbum
import com.kpstv.spotifyapi.data.models.Artists
import com.kpstv.spotifyapi.enumerations.Groups

class ArtistApi(
    private val client: SpotifyClient
) {
    /**
     * Get Spotify catalog information for several artists based on their Spotify IDs.
     */
    fun getMultipleArtists(ids: Array<String>, responseAction: ResponseAction<Artists>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/artists?ids=${ids.joinToString(",")}",
                type = Artists::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
     */
    fun getArtist(id: String, responseAction: ResponseAction<Artist>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/artists/${id}",
                type = Artist::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information about an artist’s albums.
     * Optional parameters can be specified in the query string to filter and sort the response.
     */
    fun getArtistAlbums(
        id: String,
        groups: Array<Groups>,
        limit: Int = 2,
        offset: Int = 5,
        responseAction: ResponseAction<ArtistAlbum>
    ) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeGETMethod(
                url = "https://api.spotify.com/v1/artists/${id}/albums?limit=${limit}&offset=${offset}&include_groups=${groups.joinToString(
                    ","
                ) { it.query }}",
                type = ArtistAlbum::class.java,
                responseAction = responseAction
            )
        }
    }
}