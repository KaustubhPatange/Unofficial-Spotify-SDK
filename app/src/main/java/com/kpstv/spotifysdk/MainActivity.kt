package com.kpstv.spotifysdk

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kpstv.spotifyapi.AuthResponse
import com.kpstv.spotifyapi.ResponseAction
import com.kpstv.spotifyapi.SpotifyClient
import com.kpstv.spotifyapi.SpotifyScopes
import com.kpstv.spotifyapi.data.models.*

class MainActivity : AppCompatActivity() {

    lateinit var client: SpotifyClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        client = SpotifyClient.Builder(this)
            .setClientId("ff0d06a6f7c943d9bb0a0e2167efaa1d")
            .setClientSecret("ea40241f918944929252a1e61bda3dcf")
            .setRedirectUrl("https://kaustubhpatange.github.io/YTPlayer")
            .setScopes(SpotifyScopes.UGC_IMAGE_UPLOAD, SpotifyScopes.APP_REMOTE_CONTROL)
            .setResultCallback(object : ResponseAction<AuthResponse>{
                override fun onComplete(t: AuthResponse) {
                    Toast.makeText(this@MainActivity, "Token: ${t.accessToken}", Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Exception) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            })
            .build()
    }

/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        client.processResponse(requestCode, resultCode, data, object : ResponseAction<AuthResponse>{
            override fun onComplete(t: AuthResponse) {
                Toast.makeText(this@MainActivity, "Token: ${t.accessToken}", Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        })
        super.onActivityResult(requestCode, resultCode, data)
    }*/

    fun connectClick(view: View) {
        // Album id = 0sNOF9WDwhWunNAHPD3Baj
       client.methods.tracksApi.getTrack("4u8Gkqyg87iGSW3hQbbnQZ", object : ResponseAction<Track>{
            override fun onComplete(t: Track) {
                Toast.makeText(this@MainActivity, "Name: ${t.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
}
