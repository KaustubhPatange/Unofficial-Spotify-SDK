package com.kpstv.spotifyapi

import com.kpstv.spotifyapi.data.methods.*

class Methods(
    client: SpotifyClient
) {
    val tracksApi = TracksApi(client)
    val albumsApi = AlbumsApi(client)
    val searchApi = SearchApi(client)
    val showsApi = ShowsApi(client)
    val episodesApi = EpisodesApi(client)
}