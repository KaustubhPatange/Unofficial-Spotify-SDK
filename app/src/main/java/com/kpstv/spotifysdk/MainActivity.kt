package com.kpstv.spotifysdk

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kpstv.spotifyapi.AuthResponse
import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.data.models.Browse
import com.kpstv.spotifyapi.enumerations.Scopes

class MainActivity : AppCompatActivity() {

    lateinit var client: SpotifyClient

    val CLIENT_ID = ""
    val CLIENT_SECRET = ""
    val REDIRECT_URI = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        client = SpotifyClient.Builder(this)
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUrl(REDIRECT_URI)
            .setScopes(Scopes.UGC_IMAGE_UPLOAD, Scopes.APP_REMOTE_CONTROL)
            .setResultCallback(object : ResponseAction<AuthResponse> {
                override fun onComplete(t: AuthResponse) {
                    Toast.makeText(this@MainActivity, "Token: ${t.accessToken}", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onError(e: Exception) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .build()
    }

    fun connectClick(view: View) {
        client.methods.browseApi.getBrowseCategoriesList(10, 5, null, null,
            object : ResponseAction<Browse> {
                override fun onComplete(t: Browse) {
                    Toast.makeText(
                        this@MainActivity,
                        "Name: ${t.categories.total}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(e: Exception) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}
