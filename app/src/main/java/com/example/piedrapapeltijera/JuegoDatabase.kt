package com.example.piedrapapeltijera

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.piedrapapeltijera.Entidades.PartidaEntity

@Database(entities = arrayOf(PartidaEntity::class), version = 1)
abstract class JuegoDatabase : RoomDatabase() {
    abstract fun juegoDao(): JuegoDao
}