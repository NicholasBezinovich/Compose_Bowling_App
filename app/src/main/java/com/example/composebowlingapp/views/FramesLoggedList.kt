package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameDataTable
import com.example.composebowlingapp.FrameLoggerActions
import com.example.composebowlingapp.GameDataTable

@Composable
fun FramesLoggedList(frameList: List<FrameDataTable>, gameList: List<GameDataTable>, onAction: (FrameLoggerActions) -> Unit = {}) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
    ) {
        Column {
            Text(
                text = "Recently Logged Frames",
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 10.dp)
            )
            Column {
                frameList.reversed().forEach {
                    Card(
                        modifier = Modifier
                            .padding(15.dp, 0.dp, 15.dp, 15.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .height(150.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 15.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(15.dp, 15.dp, 15.dp, 15.dp)
                        ) {
                            Column(modifier = Modifier
                                .weight(1f))
                            {
                                if (it.strike) {
                                    Text(
                                        text = "Strike",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                } else if (it.spare) {
                                    Text(
                                        text = "Spare",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                } else {
                                    Text(
                                        text = "Open",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                    LogPinsLeft(it)
                                }
                            }
                            Column(modifier = Modifier
                                .weight(1f))
                            {
                                Text(
                                    text = it.date,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterHorizontally)
                                )
                                TextButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(CircleShape)
                                        .background(Color.DarkGray)
                                        .width(100.dp)
                                        .align(Alignment.CenterHorizontally),
                                    onClick =
                                    {
                                        onAction(FrameLoggerActions.DeleteLog(it))
                                    }
                                ) {
                                    Text(
                                        "Delete",
                                        color = Color.White,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Text(
                text = "Recently Logged Games",
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 10.dp)
            )
            Column {
                gameList.reversed().forEach {
                    Card(
                        modifier = Modifier
                            .padding(15.dp, 0.dp, 15.dp, 15.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .height(150.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 15.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(15.dp, 15.dp, 15.dp, 15.dp)
                        ) {
                            Column(modifier = Modifier
                                .weight(1f))
                            {
                                Row()
                                {
                                    Text(
                                        text = it.gameValue.toString(),
                                        modifier = Modifier
                                            .padding(15.dp, 15.dp, 15.dp, 10.dp)
                                    )
                                }
                            }
                            Column(modifier = Modifier
                                .weight(1f))
                            {
                                TextButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(CircleShape)
                                        .background(Color.DarkGray)
                                        .width(100.dp)
                                        .align(Alignment.CenterHorizontally),
                                    onClick =
                                    {
                                        onAction(FrameLoggerActions.DeleteGame(it))
                                    }
                                ) {
                                    Text(
                                        "Delete",
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
    }
}