package com.example.composebowlingapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameDataTable(
    var gameValue: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

