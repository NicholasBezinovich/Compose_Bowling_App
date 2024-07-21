package com.example.composebowlingapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebowlingapp.FrameDataViewModel
import com.example.composebowlingapp.FrameLoggerActions
import com.example.composebowlingapp.GameLoggerViewActions
import com.example.composebowlingapp.components.FullGameView
import com.example.composebowlingapp.components.QuickFrameLogSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FullGameLoggerView(onAction: (GameLoggerViewActions) -> Unit, listOfGames: MutableList<MutableList<String>>) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {
        Color.White} else {
        Color.Black}
    var backgroundColor = if (dark) {
        Color.DarkGray} else {
        Color.White}

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {onAction(GameLoggerViewActions.AddGame("Nick"))}) {
                    Text("+", color = Color.White)
                }
            }
            listOfGames.forEach {
                FullGameView(name = "Nick", game = it) {

                }
            }
        }
    }
}

@Preview
@Composable
fun FGMLVPreview() {
    FullGameLoggerView(
        onAction = {},
        listOfGames = mutableListOf<MutableList<String>>()
    )
}