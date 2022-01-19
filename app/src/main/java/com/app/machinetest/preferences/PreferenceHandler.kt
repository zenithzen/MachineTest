package com.app.machinetest.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject




class PreferenceHandler @Inject constructor(
    val sharedPreferences: SharedPreferences,
) {

    var userToken: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) = sharedPreferences.edit { putString("token", value) }

    var isOnBoardingCompleted: Boolean
        get() = sharedPreferences.getBoolean("isOnBoardingCompleted", false)
        set(value) = sharedPreferences.edit() { putBoolean("isOnBoardingCompleted", value) }

    var isLogged: Boolean
        get() = sharedPreferences.getBoolean("isLogged", false)
        set(value) = sharedPreferences.edit() { putBoolean("isLogged", value) }
}