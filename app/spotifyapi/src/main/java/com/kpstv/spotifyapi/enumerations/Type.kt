package com.kpstv.spotifyapi.enumerations

enum class Type(val type: String) {
    ARTIST("artist"),
    ALBUM("album"),
    PLAYLIST("playlist"),
    TRACK("track"),
    SHOW("show"),
    EPISODE("episode")
}