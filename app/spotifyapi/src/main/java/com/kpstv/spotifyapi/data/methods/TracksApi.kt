package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.*

class TracksApi(
    private val client: SpotifyClient
) {
    /**
     * Get Spotify catalog information for multiple tracks based on their Spotify IDs.
     */
    fun getSeveralTracks(ids: Array<String>, responseAction: ResponseAction<Tracks>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeClient(
                url = "https://api.spotify.com/v1/tracks?ids=${ids.joinToString(",")}",
                type = Tracks::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get a detailed audio analysis for a single track identified by its unique Spotify ID.
     */
    fun getAudioAnalysis(id: String, responseAction: ResponseAction<AudioAnalysis>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeClient(
                url = "https://api.spotify.com/v1/audio-analysis/$id",
                type = AudioAnalysis::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get audio feature information for a single track identified by its unique Spotify ID.
     */
    fun getAudioFeature(id: String, responseAction: ResponseAction<AudioFeature>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeClient(
                url = "https://api.spotify.com/v1/audio-features/$id",
                type = AudioFeature::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get Spotify catalog information for a single track identified by its unique Spotify ID.
     */
    fun getTrack(id: String, responseAction: ResponseAction<Track>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeClient(
                url = "https://api.spotify.com/v1/tracks/$id",
                type = Track::class.java,
                responseAction = responseAction
            )
        }
    }

    /**
     * Get audio features for multiple tracks based on their Spotify IDs.
     */
    fun getSeveralFeatures(ids: Array<String>, responseAction: ResponseAction<AudioFeatures>) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            client.executeClient(
                url = "https://api.spotify.com/v1/audio-features?id=${ids.joinToString(",")}",
                type = AudioFeatures::class.java,
                responseAction = responseAction
            )
        }
    }

}