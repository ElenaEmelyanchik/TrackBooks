package com.example.trackbooks

import android.app.Application
import android.content.Context
import com.example.trackbooks.di.AppComponent
import com.example.trackbooks.di.ContextModule
import com.example.trackbooks.di.DaggerAppComponent
import com.example.trackbooks.di.RoomModule

class App : Application() {

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .roomModule(RoomModule())
            .build()
    }

    fun component() = component
}