package com.kpstv.spotifyapi

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.SharedPreferences
import android.os.Handler
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kpstv.spotifyapi.enumerations.Scopes
import com.spotify.sdk.android.auth.LoginActivity
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class SpotifyClient(
    private val activity: ComponentActivity
) {
    private val AUTHORIZATION_REQUEST_CODE = 616

    private var REDIRECT_URI = ""
    private var CLIENT_ID = ""
    private var CLIENT_SECRET = ""
    private var SCOPES: Array<out String>? = null
    private var RESPONSE_ACTION: ResponseAction<AuthResponse>? = null

    private var accessToken: String = ""

    val methods = Methods(this)

    val mainHandler = Handler(activity.mainLooper)

    private val gson: Gson = GsonBuilder()
        .serializeNulls()
        .create()

    private val preferences: SharedPreferences =
        activity.getSharedPreferences("com.kpstv.spotifyapi.oauth", MODE_PRIVATE)

    private var rememberToRunThisBlock: ((Boolean, AuthResponse?, java.lang.Exception?) -> Unit)? =
        null

    val comExp = Exception("Undetermined error")

    /**
     * Initiate the authorization code flow. Needs CLIENT_ID, CLIENT_SECRET,
     * REDIRECT_URI, SCOPES as well.
     *
     * This flow will generate access_token and refresh_token.
     */
    fun invokeAuthorizationFlow(): Unit = with(activity) {
        val builder = com.spotify.sdk.android.auth.AuthorizationRequest.Builder(
            CLIENT_ID, com.spotify.sdk.android.auth.AuthorizationResponse.Type.CODE,
            REDIRECT_URI
        )

        builder.setScopes(SCOPES)
        val request = builder.build()

        val intent = LoginActivity.getAuthIntent(this, request).apply {
            flags = FLAG_ACTIVITY_CLEAR_TOP
        }

        val callback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            processResponse(AUTHORIZATION_REQUEST_CODE, it.resultCode, it.data, RESPONSE_ACTION)
        }

        callback.launch(intent)
    }

    /**
     * This must be placed in onActivityResult of the calling activity.
     *
     * It will capture the response from "invokeAuthorizationFlow()" method.
     */
    fun processResponse(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        responseAction: ResponseAction<AuthResponse>?
    ) {
        if (requestCode == AUTHORIZATION_REQUEST_CODE) {
            val response = com.spotify.sdk.android.auth.AuthorizationClient.getResponse(
                resultCode,
                data
            )
            when (response.type) {
                com.spotify.sdk.android.auth.AuthorizationResponse.Type.CODE -> {
                    val authToken = response.code
                    makeAResponse(
                        "grant_type=authorization_code&code=$authToken&redirect_uri=$REDIRECT_URI"
                        , responseAction
                    )
                }
                com.spotify.sdk.android.auth.AuthorizationResponse.Type.ERROR -> {
                    responseAction?.onError(Exception("Authentication is denied"))
                }
                else ->
                    responseAction?.onError(Exception("Authentication unknown error"))
            }
        }
    }

    /**
     * This will create an access token from refresh token.
     *
     * You can either supply refreshToken manually or let the client get find
     * it from previous authorization flow.
     */
    fun createAccessTokenFromRefreshToken(
        responseAction: ResponseAction<AuthResponse>,
        refreshToken: String? = null
    ) {
        val refToken = refreshToken ?: preferences.getString("refresh_token", "")
        if (refToken.isNullOrBlank()) {
            responseAction.onError(Exception("Refresh Token is null"))
            return
        }

        makeAResponse(
            "grant_type=refresh_token&refresh_token=${refToken}&redirect_uri=${REDIRECT_URI}",
            responseAction
        )

    }


    fun commonWorkFlow(block: (Boolean, AuthResponse?, Exception?) -> Unit) =
        with(activity) {
            accessToken = preferences.getString("access_token", "") ?: ""
            val refreshToken = preferences.getString("refresh_token", "") ?: ""
            val expiresIn = preferences.getLong("expiresIn", 0)
            val todayTime = Calendar.getInstance().time.getFormattedDate().toLong()

            val response = AuthResponse(
                accessToken = accessToken,
                refreshToken = refreshToken,
                tokenType = null
            )

            if (todayTime > expiresIn) {
                if (refreshToken.isBlank()) {
                    rememberToRunThisBlock = block
                    invokeAuthorizationFlow()
                } else {
                    createAccessTokenFromRefreshToken(object : ResponseAction<AuthResponse> {
                        override fun onComplete(t: AuthResponse) {
                            block.invoke(true, t, null)
                        }

                        override fun onError(e: Exception) {
                            block.invoke(false, null, e)
                        }
                    })
                }
            } else block.invoke(true, response, null)
        }

    fun <T> executePOSTMethod(
        url: String,
        type: Class<T>,
        bodyJSON: String,
        responseAction: ResponseAction<T>
    ) {
        val body = RequestBody.create(MediaType.parse("application/json"), bodyJSON)
        commonMethod(
            method = "POST",
            url = url,
            body = body,
            type = type,
            responseAction = responseAction)
    }

    fun <T> executePUTMethod(
        url: String,
        type: Class<T>,
        bodyJSON: String,
        responseAction: ResponseAction<T>
    ) {
        val body = RequestBody.create(MediaType.parse("application/json"), bodyJSON)
        commonMethod(
            method = "PUT",
            url = url,
            body = body,
            type = type,
            responseAction = responseAction
        )
    }

    fun <T> executeDELETEMethod(
        url: String,
        type: Class<T>,
        responseAction: ResponseAction<T>
    ) {
        commonMethod(
            method = "DELETE",
            url = url,
            type = type,
            responseAction = responseAction)
    }

    fun <T> executeGETMethod(
        url: String,
        type: Class<T>,
        responseAction: ResponseAction<T>
    ) {
        commonMethod(
            method = "GET",
            url = url,
            type = type,
            responseAction = responseAction)
    }

    private fun <T> commonMethod(
        method: String,
        url: String,
        body: RequestBody? = null,
        type: Class<T>,
        responseAction: ResponseAction<T>
    ) {
        val client = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .method(method, body)
            .addHeader(
                "Authorization",
                "Bearer $accessToken"
            )
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                mainHandler.post {
                    responseAction.onError(e ?: Exception("Response not successful"))
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                mainHandler.post {
                    if (response?.isSuccessful == true) {
                        val json = response.body().string()
                        if (json == null) {
                            responseAction.onError(Exception("Response is null"))
                            return@post
                        }

                        val classType = gson.fromJson(json, type)
                        responseAction.onComplete(classType)
                    } else
                        responseAction.onError(Exception("Response not successful"))
                }
            }
        })
    }

    private fun makeAResponse(content: String, responseAction: ResponseAction<AuthResponse>?) {
        val mediaType = MediaType.parse("application/x-www-form-urlencoded")
        val body = RequestBody.create(
            mediaType,
            content
        )

        val base64String =
            Base64.encodeToString("${CLIENT_ID}:${CLIENT_SECRET}".toByteArray(), Base64.DEFAULT)
                .replace("\n", "")

        val request = Request.Builder()
            .url("https://accounts.spotify.com/api/token")
            .method("POST", body)
            .addHeader("Authorization", "Basic $base64String")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()

        val client = OkHttpClient().newBuilder().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                rememberToRunThisBlock = null
                mainHandler.post {
                    responseAction?.onError(e ?: Exception("Request failed"))
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                mainHandler.post {
                    if (response?.isSuccessful == true) {
                        val json = response.body().string()
                        if (json == null) {
                            responseAction?.onError(Exception("Response is null"))
                            return@post
                        }
                        val authResponse = gson.fromJson(json, AuthResponse::class.java)

                        accessToken = authResponse.accessToken

                        /** Saving refresh token to preference */

                        val date = Calendar.getInstance().apply {
                            // 55 minutes coz setting 1 hour might produce some ambiguity
                            add(Calendar.MINUTE, 55)
                        }.time

                        preferences.edit().apply {
                            putString("refresh_token", authResponse.refreshToken)
                            putLong("expiresIn", date.getFormattedDate().toLong())
                            putString("access_token", authResponse.accessToken)
                        }.apply()

                        rememberToRunThisBlock?.invoke(true, authResponse, null)

                        responseAction?.onComplete(authResponse)

                    } else
                        responseAction?.onError(Exception("Response not successful"))

                    rememberToRunThisBlock = null
                }
            }
        })
    }


    class Builder(
        private val activity: ComponentActivity
    ) {
        private var clientId = ""
        private var clientSecret = ""
        private var redirectUri = ""
        private var scopes: Array<out String>? = null
        private var responseAction: ResponseAction<AuthResponse>? = null

        fun setClientId(id: String) = with(id) {
            clientId = this
            this@Builder
        }

        fun setClientSecret(secret: String) = with(secret) {
            clientSecret = this
            this@Builder
        }

        fun setRedirectUrl(url: String) = with(url) {
            redirectUri = this
            this@Builder
        }

        fun setScopes(vararg scopes: Scopes) = with(scopes) {
            val list = ArrayList<String>()
            for (scope in scopes) {
                list.add(scope.scope)
            }
            this@Builder.scopes = list.toTypedArray()
            this@Builder
        }

        fun setResultCallback(responseAction: ResponseAction<AuthResponse>) = with(responseAction) {
            this@Builder.responseAction = responseAction
            this@Builder
        }

        fun build() = with(SpotifyClient(activity)) {
            CLIENT_ID = clientId
            CLIENT_SECRET = clientSecret
            REDIRECT_URI = redirectUri
            SCOPES = scopes
            RESPONSE_ACTION = responseAction
            this
        }
    }
}