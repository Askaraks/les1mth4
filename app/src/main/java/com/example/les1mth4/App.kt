package com.example.les1mth4

import android.app.Application
import android.content.SharedPreferences
import com.example.les1mth4.utils.Prefs

class App() : Application() {
    private lateinit var preferences: SharedPreferences

    companion object{
        lateinit var pref: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext.getSharedPreferences("settings", MODE_PRIVATE)
        pref = Prefs(preferences)
    }
}