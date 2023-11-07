package com.example.piedrapapeltijera

import android.app.Application
import androidx.room.Room

class JuegoApp:Application() {
    companion object{
        lateinit var database: JuegoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        JuegoApp.database= Room.databaseBuilder(this, JuegoDatabase::class.java, "juego-db").build()
    }
}