package com.example.composebowlingapp

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.composebowlingapp.ui.theme.ComposeBowlingAppTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            BowlingDatabase::class.java,
            "bowlingData.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBowlingAppTheme {
                val viewModel by viewModels<FrameDataViewModel>(
                    factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return FrameDataViewModel(db.dao, db.gDao) as T
                            }
                        }
                    }
                )
                val state = viewModel.state
                val gameState = viewModel.gameState
                val frameDataListState = viewModel.listOfData

                Box(modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        DateFilter()
                        StatsSection(
                            strikePercent = viewModel.strikePercent.value,
                            sparePercent = viewModel.sparePercent.value,
                            openPercent = viewModel.openPercent.value,
                            averageScore = viewModel.averageGame.value
                        )
                        QuickFrameLogSection(state = state, onAction = viewModel::onAction)
                        QuickGameScoreLogged(gameState = gameState, onAction = viewModel::onAction)
                        FramesLoggedList(list = frameDataListState, onAction = viewModel::onAction)
                    }
                }
            }
        }
    }
}

@Composable
fun DateFilter() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
    ) {
        Column() {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Card(
                    modifier = Modifier
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 15.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(1.dp, Color.Black)
                        ) {
                            Text(
                                text = "Filter", modifier = Modifier.align(Center)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                        ) {
                            Text(
                                text = "Today", modifier = Modifier
                                    .align(Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatsSection(strikePercent: Double, sparePercent: Double, openPercent: Double, averageScore: Double) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .height(150.dp)
    ) {
        Column() {
            //Text Header
            Text(
                text = "Statistics", modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
            )
            Card(
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 15.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                elevation = 15.dp
            ) {
                Column {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color.Black)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(CenterHorizontally)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "Average: "+ averageScore.roundToInt(),
                                    modifier = Modifier.align(Center)
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(1.dp, Color.Black)
                            .weight(2f)
                    ) {
                        //Strikes Data Area
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Strikes",
                                    modifier = Modifier
                                        .align(Center),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "%.2f".format(strikePercent) + "%",
                                    modifier = Modifier
                                        .align(Center)
                                )
                            }
                        }

                        //Spares Data Area
                        Column(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(CenterHorizontally)
                            ) {
                                Text(
                                    text = "Spares",
                                    modifier = Modifier
                                        .align(Center),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(CenterHorizontally)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "%.2f".format(sparePercent) + "%",
                                    modifier = Modifier
                                        .align(Center)
                                )
                            }
                        }

                        //Opens Data Area
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(CenterHorizontally)
                            ) {
                                Text(
                                    text = "Opens",
                                    modifier = Modifier
                                        .align(Center),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(CenterHorizontally)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "%.2f".format(openPercent) + "%",
                                    modifier = Modifier
                                        .align(Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuickFrameLogSection(state: FrameDataTable, onAction: (FrameLoggerActions) -> Unit) {
    var strikeButtonState = remember {mutableStateOf(false)}
    var spareButtonState = remember {mutableStateOf(false)}
    var onePinButtonState = remember {mutableStateOf(false)}
    var twoPinButtonState = remember {mutableStateOf(false)}
    var threePinButtonState = remember {mutableStateOf(false)}
    var fourPinButtonState = remember {mutableStateOf(false)}
    var fivePinButtonState = remember {mutableStateOf(false)}
    var sixPinButtonState = remember {mutableStateOf(false)}
    var sevenPinButtonState = remember {mutableStateOf(false)}
    var eightPinButtonState = remember {mutableStateOf(false)}
    var ninePinButtonState = remember {mutableStateOf(false)}
    var tenPinButtonState = remember {mutableStateOf(false)}

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .height(250.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ) {
            Text(
                text = "Log Frame",
                modifier = Modifier
                    .padding(0.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                elevation = 10.dp,
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color.White
            ) {
                Row(modifier = Modifier
                    .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .background(Color.White),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .width(170.dp),
                            contentAlignment = Center
                        ) {
                            Row() {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            sevenPinButtonState.value = !sevenPinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("7", sevenPinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (sevenPinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "7",
                                            color = if (sevenPinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            eightPinButtonState.value = !eightPinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("8", eightPinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (eightPinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "8",
                                            color = if (eightPinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            ninePinButtonState.value = !ninePinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("9", ninePinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (ninePinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "9",
                                            color = if (ninePinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            tenPinButtonState.value = !tenPinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("10", tenPinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (tenPinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "10",
                                            color = if (tenPinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .width(130.dp),
                            contentAlignment = Center
                        ) {
                            Row() {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            fourPinButtonState.value = !fourPinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("4", fourPinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (fourPinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "4",
                                            color = if (fourPinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            fivePinButtonState.value = !fivePinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("5", fivePinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (fivePinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "5",
                                            color = if (fivePinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            sixPinButtonState.value = !sixPinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("6", sixPinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (sixPinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "6",
                                            color = if (sixPinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .width(90.dp),
                            contentAlignment = Center
                        ) {
                            Row() {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            twoPinButtonState.value = !twoPinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("2", twoPinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (twoPinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "2",
                                            color = if (twoPinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            threePinButtonState.value = !threePinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("3", threePinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (threePinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "3",
                                            color = if (threePinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .width(50.dp),
                            contentAlignment = Center
                        ) {
                            Row() {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxSize(),
                                    contentAlignment = Center
                                ) {
                                    TextButton(
                                        onClick =
                                        {
                                            onePinButtonState.value = !onePinButtonState.value
                                            onAction(FrameLoggerActions.TappedPin("1", onePinButtonState.value))
                                        },
                                        modifier = Modifier
                                            .background(
                                                if (onePinButtonState.value) Color.Black else Color.Transparent,
                                                CircleShape
                                            )
                                            .border(
                                                2.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .height(40.dp)
                                            .width(40.dp)
                                    ) {
                                        Text(
                                            "1",
                                            color = if (onePinButtonState.value) Color.White else Color.Black
                                        )
                                    }
                                }
                            }
                        }

                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .background(Color.White)
                            .padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .padding(5.dp)
                        ) {
                            TextButton(
                                onClick =
                                {
                                    spareButtonState.value = !spareButtonState.value
                                    strikeButtonState.value = false
                                    onAction(FrameLoggerActions.TappedMark("spare", spareButtonState.value))
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .background(
                                        if (spareButtonState.value) {
                                            Color.Black
                                        } else {
                                            Color.White
                                        },
                                        RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        2.dp,
                                        Color.Black,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .weight(1f)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    "/",
                                    color = if (spareButtonState.value) {
                                        Color.White
                                    } else {
                                        Color.Black
                                    },
                                    fontSize = 45.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            TextButton(
                                onClick =
                                {
                                    strikeButtonState.value = !strikeButtonState.value
                                    spareButtonState.value = false
                                    onAction(FrameLoggerActions.TappedMark("strike", strikeButtonState.value))
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .background(
                                        if (strikeButtonState.value) {
                                            Color.Black
                                        } else {
                                            Color.White
                                        },
                                        RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        2.dp,
                                        Color.Black,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .weight(1f)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    "X",
                                    color = if (strikeButtonState.value) {
                                        Color.White
                                    } else {
                                        Color.Black
                                    },
                                    fontSize = 45.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .padding(5.dp)
                        ) {
                            TextButton(
                                onClick =
                                {
                                    onAction(FrameLoggerActions.Enter)
                                    onePinButtonState.value = false
                                    twoPinButtonState.value = false
                                    threePinButtonState.value = false
                                    fourPinButtonState.value = false
                                    fivePinButtonState.value = false
                                    sixPinButtonState.value = false
                                    sevenPinButtonState.value = false
                                    eightPinButtonState.value = false
                                    ninePinButtonState.value = false
                                    tenPinButtonState.value = false
                                    strikeButtonState.value = false
                                    spareButtonState.value = false

                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.DarkGray)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    "Enter",
                                    color = Color.White,
                                    fontSize = 45.sp
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuickGameScoreLogged(gameState: GameDataState, onAction: (FrameLoggerActions) -> Unit) {
    var scoreText = remember {mutableStateOf("")}

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
                            label = {Text("EnterScore")}
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

@Composable
fun FramesLoggedList(list: List<FrameDataTable>, onAction: (FrameLoggerActions) -> Unit = {}) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
    ) {
        Column {
            Text(
                text = "Recently Logged",
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 10.dp)
            )
            Column {
                list.reversed().forEach {
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
                            Column {
                                if (it.strike) {
                                    Text(
                                        text = "Strike",
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                    )
                                } else if (it.spare) {
                                    Text(
                                        text = "Spare",
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                    )
                                } else {
                                    Text(
                                        text = "Open",
                                        modifier = Modifier
                                            .align(CenterHorizontally)
                                    )
                                }
                                Box(modifier = Modifier.align(CenterHorizontally)) {
                                    LogPinsLeft(it)
                                }
                            }
                            Text(
                                text = "N/A",
                                modifier = Modifier
                                    .align(CenterVertically)
                            )

                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Column(modifier = Modifier
                                .weight(1f))
                            {
                                Text(
                                    text = it.date,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(CenterHorizontally)
                                )
                                TextButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(CircleShape)
                                        .background(Color.DarkGray)
                                        .width(100.dp)
                                        .align(CenterHorizontally),
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
    }
}

@Composable
fun LogPinsLeft(data: FrameDataTable) {
    Column {
        Row(modifier = Modifier.align(CenterHorizontally)) {
            if (data.pin7) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
            if (data.pin8) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
            if (data.pin9) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
            if (data.pin10) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
        }
        Row(modifier = Modifier.align(CenterHorizontally)) {
            if (data.pin4) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
            if (data.pin5) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
            if (data.pin6) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
        }
        Row(modifier = Modifier.align(CenterHorizontally)) {
            if (data.pin2) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
            if (data.pin3) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
        }
        Row(modifier = Modifier.align(CenterHorizontally)) {
            if (data.pin1) {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black)})
            } else {
                Canvas(modifier = Modifier.size(20.dp), onDraw = {drawCircle(color = Color.Black, style = Stroke(width = 5f))})
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeBowlingAppTheme {
        //val viewModel = viewModel<FrameDataViewModel>()
        //val state = viewModel.state
        //val gameState = viewModel.gameState
        var frameDataList = mutableListOf<FrameDataTable>()
        frameDataList.add(FrameDataTable())
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DateFilter()
                StatsSection(
                    strikePercent = 0.0,
                    sparePercent = 0.0,
                    openPercent = 0.0,
                    averageScore = 0.0
                )
                //QuickFrameLogSection(state = state, onAction = viewModel::onAction)
                //QuickGameScoreLogged(gameState = gameState, onAction = viewModel::onAction)
                FramesLoggedList(list = frameDataList)
            }
        }
    }
}
