package com.example.composebowlingapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class GameDataTable(
    var gameValue: Int = 0,
    var date: String = LocalDate.now().toString(),
    var profile: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

