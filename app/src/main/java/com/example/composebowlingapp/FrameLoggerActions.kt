package com.example.composebowlingapp

import com.example.composebowlingapp.views.DateType

sealed class FrameLoggerActions {
    data class TappedPin(val pin: String, val onState: Boolean): FrameLoggerActions()
    data class TappedMark(val mark: String, val onState: Boolean): FrameLoggerActions()
    data class EnterGame(val gameScore: Int): FrameLoggerActions()
    data class DeleteGame(val game: GameDataTable): FrameLoggerActions()
    data class DeleteLog(val frame: FrameDataTable): FrameLoggerActions()
    data class DateFilterChanged(val dateType: DateType): FrameLoggerActions()
    data class ToggleShowFrameList(val b: Boolean): FrameLoggerActions()
    data class ToggleShowGameList(val b: Boolean): FrameLoggerActions()
    data class ProfileToggled(val b: Boolean): FrameLoggerActions()
    object Enter: FrameLoggerActions()
}
