package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameLoggerActions

@Composable
fun QuickGameScoreLogged(onAction: (FrameLoggerActions) -> Unit) {
    var scoreText = remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .height(130.dp)
    ) {
        Column {
            Text(
                text = "Game Score",
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 0.dp)
            )
            Card (
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 15.dp)
                    .fillMaxSize()
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                elevation = 10.dp,
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color.White
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)) {
                        TextField(
                            value = scoreText.value,
                            onValueChange = { scoreText.value = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text("EnterScore") }
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color.DarkGray),
                            onClick =
                            {
                                if (scoreText.value != "") {
                                    onAction(FrameLoggerActions.EnterGame(scoreText.value.toInt()))
                                    scoreText.value = ""
                                }


                            }
                        ) {
                            Text(
                                "Submit",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
    }
}