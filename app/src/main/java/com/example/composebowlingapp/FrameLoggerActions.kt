package com.example.composebowlingapp

import com.example.composebowlingapp.components.DateType

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
    data class FilterRemoved(val title: String): FrameLoggerActions()
    data class AddToFilterList(val title: String): FrameLoggerActions()
    data class AddToAppliedFilterList(val title: String): FrameLoggerActions()
    data class RemoveFromAppliedFilterList(val title: String): FrameLoggerActions()
    object Enter: FrameLoggerActions()
}
