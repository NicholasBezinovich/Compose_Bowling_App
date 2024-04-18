package com.example.composebowlingapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FrameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFrame(frame: FrameDataTable)

    @Delete
    suspend fun deleteFrame(frame: FrameDataTable)

    @Query("SELECT * FROM frameDataTable")
    fun getAllData(): Flow<List<FrameDataTable>>
}