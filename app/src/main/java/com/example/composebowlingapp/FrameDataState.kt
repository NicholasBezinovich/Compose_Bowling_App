package com.example.composebowlingapp

import java.time.LocalDate
import java.util.Date
import java.util.UUID

data class FrameDataState(
    var pin1: Boolean = false,
    var pin2: Boolean = false,
    var pin3: Boolean = false,
    var pin4: Boolean = false,
    var pin5: Boolean = false,
    var pin6: Boolean = false,
    var pin7: Boolean = false,
    var pin8: Boolean = false,
    var pin9: Boolean = false,
    var pin10: Boolean = false,
    var strike: Boolean = false,
    var spare: Boolean = false,
    var date: String = LocalDate.now().toString(),
    var id: Int = 0
)
