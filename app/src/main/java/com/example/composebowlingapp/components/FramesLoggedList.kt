package com.example.composebowlingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameDataTable
import com.example.composebowlingapp.FrameDataViewModel
import com.example.composebowlingapp.FrameLoggerActions
import com.example.composebowlingapp.GameDataTable

@Composable
@NonRestartableComposable
fun FramesLoggedList(frameList: List<FrameDataTable>,
                     gameList: List<GameDataTable>,
                     onAction: (FrameLoggerActions) -> Unit = {},
                     viewModel: FrameDataViewModel) {
    var showFrameDetailsPopup = remember {mutableStateOf(false)}
    var frameDataTapped = remember {mutableStateOf(FrameDataTable())}
    var showGameDetailsPopup = remember {mutableStateOf(false)}
    var gameDataTapped = remember {mutableStateOf(GameDataTable())}

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)) {
            Card(
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 15.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .height(40.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                elevation = 15.dp
            ) {
                Button(
                    onClick = {
                        onAction(FrameLoggerActions.ToggleShowFrameList(b = !viewModel.showFrameList.value))
                    },
                    colors = ButtonDefaults.buttonColors(Color.DarkGray)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Recently Logged Frames",
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                                .align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.weight(2f))
                        Text(
                            text = if (viewModel.showFrameList.value) "v" else ">",
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                                .align(CenterVertically)
                        )
                    }
                }
            }
            Column {
                if (viewModel.showFrameList.value) {
                    frameList.reversed().forEach {
                        Card(
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 15.dp, 15.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                                .height(150.dp)
                                .clickable
                                {
                                    frameDataTapped.value = it
                                    showFrameDetailsPopup.value = true
                                },
                            shape = RoundedCornerShape(10.dp),
                            elevation = 15.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                )
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
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                )
                                {
                                    Text(
                                        text = it.profile,
                                        modifier = Modifier
                                            .weight(1f)
                                            .align(Alignment.CenterHorizontally)
                                    )
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
            }
            if (showFrameDetailsPopup.value) {
                AlertDialog(
                    onDismissRequest =
                    {
                        showFrameDetailsPopup.value = false
                    },
                    buttons =
                    {
                        Box(
                            modifier = Modifier
                                .padding(15.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text("Frame Details")
                                Text("Profile: " + frameDataTapped.value.profile)
                                Text("Date: " + frameDataTapped.value.date)
                                Text("Tags: " + frameDataTapped.value.tags)
                            }
                        }
                    }
                )
            }
            Card(
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 15.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .height(40.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                elevation = 15.dp
            ) {
                Button(
                    onClick = {
                        onAction(FrameLoggerActions.ToggleShowGameList(b = !viewModel.showGameList.value))
                    },
                    colors = ButtonDefaults.buttonColors(Color.DarkGray)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Recently Logged Games",
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                                .align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.weight(2f))
                        Text(
                            text = if (viewModel.showGameList.value) "v" else ">",
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                                .align(CenterVertically)
                        )
                    }
                }
            }
            Column {
                if (viewModel.showGameList.value) {
                    gameList.reversed().forEach {
                        Card(
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 15.dp, 15.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                                .height(125.dp)
                                .clickable
                                {
                                    gameDataTapped.value = it
                                    showGameDetailsPopup.value = true
                                },
                            shape = RoundedCornerShape(10.dp),
                            elevation = 15.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                    ) {
                                        Text(
                                            text = it.profile,
                                            textAlign = TextAlign.Center,
                                            fontSize = 35.sp,
                                            modifier = Modifier
                                                .padding(0.dp)
                                                .fillMaxSize()
                                                .align(CenterVertically)
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                    ) {
                                        Text(
                                            text = it.gameValue.toString(),
                                            textAlign = TextAlign.Center,
                                            fontSize = 40.sp,
                                            modifier = Modifier
                                                .padding(0.dp)
                                                .fillMaxSize()
                                                .align(CenterVertically)
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                )
                                {
                                    Text(
                                        text = it.date,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .padding(0.dp)
                                            .fillMaxSize()
                                            .weight(1f)
                                    )
                                    TextButton(
                                        modifier = Modifier
                                            .weight(2f)
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
                        if (showGameDetailsPopup.value) {
                            AlertDialog(
                                onDismissRequest =
                                {
                                    showGameDetailsPopup.value = false
                                },
                                buttons =
                                {
                                    Box(
                                        modifier = Modifier
                                            .padding(15.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(14.dp),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Text("Game Details")
                                            Text("Profile: " + gameDataTapped.value.profile)
                                            Text("Date: " + gameDataTapped.value.date)
                                            Text("Score: " + gameDataTapped.value.gameValue)
                                            Text("Tags: " + gameDataTapped.value.tags)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

/*@Preview
@Composable
fun DefaultPreview() {
    var frameDataList = mutableListOf<FrameDataTable>()
    frameDataList.add(FrameDataTable())

    var gameDataList = mutableListOf<GameDataTable>()
    gameDataList.add(GameDataTable(gameValue = 250))

    FramesLoggedList(frameList = frameDataList, gameList = gameDataList)
}*/

