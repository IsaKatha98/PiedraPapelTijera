package com.example.piedrapapeltijera

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.piedrapapeltijera.Entidades.PartidaEntity

//Esta es la parte que hace las veces de API, es decir, permite interactuar con la base de datos.
@Dao
interface JuegoDao {

    // Función que devuelve todas las tareas de la base de datos en una lista Mutable.
    @Query("SELECT * FROM partida_entity")
    suspend fun getAllPartidas(): MutableList< PartidaEntity>

    // Función que añade una partida, la que se pasa por parámetro, y devuelve el id insertado.
    // Devuelve Long porque la cantidad de datos guardada puede ser muy alto.
    //En caso de que se genere una partida con un id que ya existe, lo ignorará
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPartida(partidaEntity : PartidaEntity):Long

    // Función que busca una partida concreta por id (debe ser Long, no Int)

    @Query("SELECT * FROM partida_entity where id like :id")
    suspend fun getPartidaById(id: Long): PartidaEntity

    // Función que actualiza una partida y devuelve
    @Update
    suspend fun updatePartida(partida: PartidaEntity):Int

    //Función que elimina una partida
    @Delete
    suspend fun deletePartida(partida: PartidaEntity):Int
}
