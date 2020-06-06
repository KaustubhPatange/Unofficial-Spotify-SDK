package com.kpstv.spotifyapi.data.methods

import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Browse

class BrowseApi(
    private val client: SpotifyClient
) {
    /**
     * Get a List of Browse Categories
     */
    fun getBrowseCategoriesList(
        limit: Int = 10,
        offset: Int = 5,
        country: String? = null,
        locale: String? = null,
        responseAction: ResponseAction<Browse>
    ) {
        client.commonWorkFlow { b, _, exception ->
            if (!b) {
                responseAction.onError(exception ?: client.comExp)
                return@commonWorkFlow
            }

            /** Country & locale are optional parameter **/

            var url = "https://api.spotify.com/v1/browse/categories?limit=${limit}&offset=${offset}"
            if (country != null) url += "&country=${country}"
            if (locale != null) url += "&locale=${locale}"

            client.executeGETMethod(
                url = url,
                type = Browse::class.java,
                responseAction = responseAction
            )
        }
    }
}