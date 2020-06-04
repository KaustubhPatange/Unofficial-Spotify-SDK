package com.kpstv.spotifyapi

import com.kpstv.spotifyapi.data.methods.AlbumsApi
import com.kpstv.spotifyapi.data.methods.TracksApi

class Methods(
    client: SpotifyClient
) {
    val tracksApi = TracksApi(client)
    val albumsApi = AlbumsApi(client)
}