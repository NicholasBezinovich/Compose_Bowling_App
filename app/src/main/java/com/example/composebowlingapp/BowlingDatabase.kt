package com.example.composebowlingapp
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FrameDataTable::class,
        GameDataTable::class],
    version = 5,
    exportSchema = false
)
abstract class BowlingDatabase: RoomDatabase() {
    abstract val dao: FrameDao
    abstract val gDao: GameDao
}