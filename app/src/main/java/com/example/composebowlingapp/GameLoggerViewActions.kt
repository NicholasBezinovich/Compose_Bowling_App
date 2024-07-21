package com.example.composebowlingapp


sealed class GameLoggerViewActions {
    data class AddGame(val name: String): GameLoggerViewActions()
}