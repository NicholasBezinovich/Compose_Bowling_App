package com.example.composebowlingapp

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class FullGameLoggerViewModel(): ViewModel()
{

    var fullGamesList = mutableListOf<MutableList<String>>()

    fun onAction(actions: GameLoggerViewActions) {
        when (actions) {
            is GameLoggerViewActions.AddGame -> addGame(actions.name)
        }
    }

    fun addGame(name: String) {
        fullGamesList.add(mutableListOf<String>("", "", "", "", "", "", "", "", "", ""))
        println("A new game has been added to the list: "+fullGamesList)
    }

    fun removeGame(item: MutableList<String>) {
        fullGamesList.remove(item)
    }
}