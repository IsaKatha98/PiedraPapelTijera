package com.example.piedrapapeltijera.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="partida_entity")
data class PartidaEntity(
    //La primary key es la variable siguiente, en este caso, id.
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var nickUsuario:String = "",
    var numPartidas:Int=0,
    var maxPuntuacion:Int=0
)
