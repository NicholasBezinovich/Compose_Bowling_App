package com.example.composebowlingapp

sealed class FrameLoggerActions {
    data class TappedPin(val pin: String, val onState: Boolean): FrameLoggerActions()
    data class TappedMark(val mark: String, val onState: Boolean): FrameLoggerActions()
    data class EnterGame(val gameScore: Int): FrameLoggerActions()
    data class DeleteGame(val game: GameDataTable): FrameLoggerActions()
    data class DeleteLog(val frame: FrameDataTable): FrameLoggerActions()
    object Enter: FrameLoggerActions()
}
