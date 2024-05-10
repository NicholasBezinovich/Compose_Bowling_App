package com.example.composebowlingapp.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.composebowlingapp.FrameDataTable

@Composable
fun LogPinsLeft(data: FrameDataTable) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {Color.White} else {Color.Black}
    Column {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (data.pin7) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
            if (data.pin8) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
            if (data.pin9) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
            if (data.pin10) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (data.pin4) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
            if (data.pin5) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
            if (data.pin6) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (data.pin2) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
            if (data.pin3) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (data.pin1) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = textColor, style = Stroke(width = 5f))})
            }
        }
    }
}
