package com.example.composebowlingapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(frame: GameDataTable)

    @Delete
    suspend fun deleteGame(frame: GameDataTable)

    @Query("SELECT * FROM gameDataTable")
    fun getAllData(): Flow<List<GameDataTable>>
}