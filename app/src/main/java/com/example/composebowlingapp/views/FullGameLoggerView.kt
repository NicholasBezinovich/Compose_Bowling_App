package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebowlingapp.FrameDataViewModel
import com.example.composebowlingapp.FrameLoggerActions
import com.example.composebowlingapp.components.QuickFrameLogSection

@Composable
fun FullGameLoggerView(onAction: (FrameLoggerActions) -> Unit) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {
        Color.White} else {
        Color.Black}
    var backgroundColor = if (dark) {
        Color.DarkGray} else {
        Color.White}
    var fullGameList = remember {mutableListOf<String>("", "", "", "", "", "", "", "", "", "")}

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(backgroundColor)) {
            Text("Profile: Nick")
            Row {
                fullGameList.forEach() {
                    Box(
                        modifier = Modifier
                            .border(1.dp, Color.Black)
                            .weight(1f)
                    ) {
                        Column() {
                            Row {
                                Text(
                                    "5",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Box(
                                    modifier = Modifier
                                        .border(1.dp, Color.Black)
                                        .align(Alignment.CenterVertically)
                                        .weight(1f)
                                ) {
                                    Row {
                                        Text(
                                            "/",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                            Row {
                                Text("10")
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun FGMLVPreview() {
    FullGameLoggerView(onAction = {})
}