package com.kpstv.spotifyapi

interface ResponseAction<T> {
    fun onComplete(t: T)
    fun onError(e: Exception)
}