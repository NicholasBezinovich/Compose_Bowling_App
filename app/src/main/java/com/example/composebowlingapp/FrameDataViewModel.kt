package com.example.composebowlingapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class FrameDataViewModel(
    private val frameDao: FrameDao,
    private val gameDao: GameDao
): ViewModel() {
    var gameState by mutableStateOf(GameDataState())
        private set
    var state by mutableStateOf(FrameDataTable())
        private set
    var listOfData = mutableStateListOf<FrameDataTable>()
        private set
    var listOfGames = mutableStateListOf<Pair<GameDataState, Int>>()
        private set

    var strikePercent: MutableState<Double> = mutableStateOf(0.0)
    var sparePercent: MutableState<Double> = mutableStateOf(0.0)
    var openPercent: MutableState<Double> = mutableStateOf(0.0)
    var averageGame: MutableState<Double> = mutableStateOf(0.0)

    init {
        fillInitalList()
    }

    private fun fillInitalList() {
        viewModelScope.launch() {
            frameDao.getAllData().first().forEach {
                listOfData.add(
                    /*FrameDataState(
                        pin1 = it.pin1,
                        pin2 = it.pin2,
                        pin3 = it.pin3,
                        pin4 = it.pin4,
                        pin5 = it.pin5,
                        pin6 = it.pin6,
                        pin7 = it.pin7,
                        pin8 = it.pin8,
                        pin9 = it.pin9,
                        pin10 = it.pin10,
                        strike = it.strike,
                        spare = it.spare,
                        date = it.date,
                        id = it.id)*/
                    it
                )
            }
            gameDao.getAllData().first().forEach {
                listOfGames.add(
                    Pair(
                        GameDataState(gameValue = it.gameValue),
                        it.occurences
                    )
                )
            }
            setStatistics()
        }
    }

    private fun updateFrameList() {
        listOfData.clear()
        viewModelScope.launch() {
            frameDao.getAllData().first().forEach {
                listOfData.add(
                    /*FrameDataState(
                        pin1 = it.pin1,
                        pin2 = it.pin2,
                        pin3 = it.pin3,
                        pin4 = it.pin4,
                        pin5 = it.pin5,
                        pin6 = it.pin6,
                        pin7 = it.pin7,
                        pin8 = it.pin8,
                        pin9 = it.pin9,
                        pin10 = it.pin10,
                        strike = it.strike,
                        spare = it.spare,
                        date = it.date,
                        id = it.id)*/
                it
                )
            }
        }
    }

    fun onAction(actions: FrameLoggerActions) {
        when (actions) {
            is FrameLoggerActions.TappedPin -> pinTapped(actions.pin, actions.onState)
            is FrameLoggerActions.TappedMark -> markTapped(actions.mark, actions.onState)
            is FrameLoggerActions.Enter -> enterTapped()
            is FrameLoggerActions.EnterGame -> enterGame(actions.gameScore)
            is FrameLoggerActions.DeleteLog -> deleteLog(actions.frame)
        }
    }
    private fun enterTapped() {
        if (!state.strike &&
            !state.spare &&
            !state.pin1 &&
            !state.pin2 &&
            !state.pin3 &&
            !state.pin4 &&
            !state.pin5 &&
            !state.pin6 &&
            !state.pin7 &&
            !state.pin8 &&
            !state.pin9 &&
            !state.pin10) {
            return
        }
        if (state.strike) {
            state.pin1 = false
            state.pin2 = false
            state.pin3 = false
            state.pin4 = false
            state.pin5 = false
            state.pin6 = false
            state.pin7 = false
            state.pin8 = false
            state.pin9 = false
            state.pin10 = false
        }

        //Save the state to a list
        var frameData = FrameDataTable()


        frameData = state//createNewDataEntry(state)


        //Update Database
        viewModelScope.launch {
            //Get date info on frame data to re add late
            frameDao.insertFrame(frameData)

            //updateFrameList()
            println(frameDao.getAllData().first())
        }
        listOfData.add(frameData)
        setStatistics()
        state = FrameDataTable()
    }

    private fun syncNewFrame(frame: FrameDataTable): FrameDataState {
        return FrameDataState(
            pin1 = frame.pin1,
            pin2 = frame.pin2,
            pin3 = frame.pin3,
            pin4 = frame.pin4,
            pin5 = frame.pin5,
            pin6 = frame.pin6,
            pin7 = frame.pin7,
            pin8 = frame.pin8,
            pin9 = frame.pin9,
            pin10 = frame.pin10,
            strike = frame.strike,
            spare = frame.spare,
            date = frame.date,
            id = frame.id
        )
    }

    private fun deleteLog(frame: FrameDataTable) {
        if (listOfData.contains(frame)) {
            println("Deleting: " + listOfData.indexOf(frame))
            val index = listOfData.indexOf(frame)
            println("NumberOfEntry: " + listOfData.count())
            viewModelScope.launch {
                frameDao.deleteFrame(frame)
                //getMatchingDataEntry(frame)
                updateFrameList()
                setStatistics()
            }
        }

    }

    private fun pinTapped(pin: String, onState: Boolean) {
        when (pin) {
            "1" -> {
                state.pin1 = onState
            }
            "2" -> {
                state.pin2 = onState
            }
            "3" -> {
                state.pin3 = onState
            }
            "4" -> {
                state.pin4 = onState
            }
            "5" -> {
                state.pin5 = onState
            }
            "6" -> {
                state.pin6 = onState
            }
            "7" -> {
                state.pin7 = onState
            }
            "8" -> {
                state.pin8 = onState
            }
            "9" -> {
                state.pin9 = onState
            }
            "10" -> {
                state.pin10 = onState
            }
        }
    }

    private fun markTapped(mark: String, onState: Boolean) {
        when (mark) {
            "strike" -> {
                state.strike = onState
                state.spare = false
            }
            "spare" -> {
                state.spare = onState
                state.strike = false
            }
        }
    }

    private fun setStatistics() {
        //Get statistics values
        var totalEntries = listOfData.count()
        var totalSpares = 0
        var totalStrikes = 0
        var totalOpens = 0
        var totalPinFall = 0
        var numberOfGames = 0
        listOfData.forEach {
            if (it.spare) {
                totalSpares++
            } else if (it.strike) {
                totalStrikes++
            } else {
                totalOpens++
            }
        }

        listOfGames.forEach {
            numberOfGames += it.second
            totalPinFall += (it.second * it.first.gameValue)
        }

        if (numberOfGames > 0) {
            averageGame.value = (totalPinFall.toDouble()/numberOfGames.toDouble())
        } else {
            averageGame.value = 0.0
        }
        if (totalEntries > 0) {
            strikePercent.value = (totalStrikes.toDouble() / totalEntries) * 100
            sparePercent.value = (totalSpares.toDouble() / totalEntries) * 100
            openPercent.value = (totalOpens.toDouble() / totalEntries) * 100
        } else {
            strikePercent.value = 0.0
            sparePercent.value = 0.0
            openPercent.value = 0.0
        }
    }

    private suspend fun getMatchingDataEntry(frame: FrameDataTable) {
        /*var deleteFrame: FrameDataTable = FrameDataTable(
            pin1 = frame.pin1,
            pin2 = frame.pin2,
            pin3 = frame.pin3,
            pin4 = frame.pin4,
            pin5 = frame.pin5,
            pin6 = frame.pin6,
            pin7 = frame.pin7,
            pin8 = frame.pin8,
            pin9 = frame.pin9,
            pin10 = frame.pin10,
            strike = frame.strike,
            spare = frame.spare,
            date = frame.date,
            id = frame.id
        )*/
        frameDao.deleteFrame(frame)
    }

    private fun createNewDataEntry(data: FrameDataState): FrameDataTable {
        return FrameDataTable(
            pin1 = data.pin1,
            pin2 = data.pin2,
            pin3 = data.pin3,
            pin4 = data.pin4,
            pin5 = data.pin5,
            pin6 = data.pin6,
            pin7 = data.pin7,
            pin8 = data.pin8,
            pin9 = data.pin9,
            pin10 = data.pin10,
            strike = data.strike,
            spare = data.spare,
            date = data.date
        )
    }

    private fun enterGame(gameScore: Int) {
        var updatedVal = false
        var gameData = GameDataTable()
        for (it in listOfGames) {
            if (it.first.gameValue == gameScore) {
                val newVal = it.copy(second = it.second + 1)
                gameData.gameValue = newVal.first.gameValue
                gameData.occurences = newVal.second
                listOfGames.add(newVal)
                listOfGames.remove(it)
                updatedVal = true
                break
            }
        }

        if (!updatedVal) {
            listOfGames.add(Pair(GameDataState(gameScore), 1))
            gameData = GameDataTable(gameScore, 1)
        }

        var totalGames: Double = 0.0
        var totalPinFall: Double = 0.0
        for (it in listOfGames) {
            totalPinFall += it.first.gameValue
            totalGames += it.second
        }

        //Update Database
        viewModelScope.launch {
            if (gameData.occurences > 1) {
                getMatchingGameDataEntry(gameData, gameDao.getAllData().first())
            }
            gameDao.insertGame(gameData)
            println(gameDao.getAllData().first())
        }

        averageGame.value = (totalPinFall / totalGames)
        gameState = GameDataState()
    }

    suspend fun getMatchingGameDataEntry(data: GameDataTable, table: List<GameDataTable>) {
        table.forEach {
            if (it.gameValue == data.gameValue) {
                gameDao.deleteGame(it)
            }
        }
    }
}