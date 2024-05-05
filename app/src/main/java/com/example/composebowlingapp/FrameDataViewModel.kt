package com.example.composebowlingapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebowlingapp.views.DateType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class FrameDataViewModel(
    private val frameDao: FrameDao,
    private val gameDao: GameDao
): ViewModel() {
    var gameState by mutableStateOf(GameDataTable())
        private set
    var state by mutableStateOf(FrameDataTable())
        private set
    var listOfData = mutableStateListOf<FrameDataTable>()
        private set
    var listOfGames = mutableStateListOf<GameDataTable>()
        private set
    var dateType = DateType.TODAY
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
                listOfData.add(it)
            }
            gameDao.getAllData().first().forEach {
                listOfGames.add(it)
            }
            setStatistics()
        }
    }

    private fun updateFrameList() {
        listOfData.clear()
        print("Print: After removing all data")
        print("Print: " + listOfData.count())
        viewModelScope.launch() {
            frameDao.getAllData().first().forEach {
                listOfData.add(it)
                setStatistics()
            }
        }
    }

    private fun updateGameList() {
        listOfGames.clear()
        viewModelScope.launch() {
            gameDao.getAllData().first().forEach {
                listOfGames.add(it)
                setStatistics()
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
            is FrameLoggerActions.DeleteGame -> deleteGame(actions.game)
            is FrameLoggerActions.DateFilterChanged -> dateSelected(actions.dateType)
        }
    }

    private fun dateSelected(selected: DateType) {
        dateType = selected
        if (dateType == DateType.TODAY) {
            println("Today was tapped")
        } else if (dateType == DateType.ALL) {
            println("All was tapped")
        } else {
            println("Ranged Filter was tapped")
        }
    }

    fun getFilteredList() : List<FrameDataTable> {
        when (dateType) {
            DateType.TODAY -> {
                return listOfData.filter {
                    it.date == LocalDate.now().toString()
                }
            }
            DateType.ALL -> {
                return listOfData
            }
            DateType.RANGE -> {
                return listOfData
            }
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
            println(frameDao.getAllData().first())
            updateFrameList()
            setStatistics()
        }
        state = FrameDataTable()
    }

    private fun deleteLog(frame: FrameDataTable) {
        if (listOfData.contains(frame)) {
            println("Deleting: " + listOfData.indexOf(frame))
            val index = listOfData.indexOf(frame)
            println("NumberOfEntry: " + listOfData.count())
            listOfData.remove(frame)
            viewModelScope.launch {
                println("Print Before Delete: " + frameDao.getAllData().first().count())
                var copyDaoList = frameDao.getAllData().first()
                var copyDaoFrame = copyDaoList.first { it.id == frame.id }
                frameDao.deleteFrame(frameDao.getAllData().first().first { it.id == frame.id})
                println("Print After Delete: " + frameDao.getAllData().first().count())
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
        var numberOfGames = listOfGames.count()
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
            totalPinFall += it.gameValue
        }

        if (numberOfGames > 0) {
            averageGame.value = totalPinFall.toDouble() / numberOfGames.toDouble()
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

    private fun enterGame(gameScore: Int) {
        var gameData = GameDataTable(gameScore)

        //Update Database
        viewModelScope.launch {
            gameDao.insertGame(gameData)
            println(gameDao.getAllData().first())
            updateGameList()
        }

        gameState = GameDataTable()
    }

    private fun deleteGame(game: GameDataTable) {
        if (listOfGames.contains(game)) {
            listOfGames.remove(game)
            viewModelScope.launch {
                gameDao.deleteGame(game)
                setStatistics()
            }
        }
    }
}