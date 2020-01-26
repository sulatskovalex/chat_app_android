package com.github.sulatskovalex.data

import android.content.Context

class PreferencesService(context: Context) : AccessTokenProvider {
    companion object {
        const val appPrefs = "app prefs"
        const val accessTokenKey = "accessTokenKey"
        const val userIdKey = "userIdKey"
    }

    private val prefs = context.getSharedPreferences(appPrefs, Context.MODE_PRIVATE)

    override var accessToken: String
        get() = prefs.getString(accessTokenKey, "") ?: ""
        set(value) = prefs.edit().putString(accessTokenKey, value).apply()

    var userId: Long
        get() = prefs.getLong(userIdKey, -1)
        set(value) = prefs.edit().putLong(userIdKey, userId).apply()

}