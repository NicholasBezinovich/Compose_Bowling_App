package com.example.composebowlingapp.components

import com.example.composebowlingapp.FrameDataTable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameLoggerActions
import kotlin.math.roundToInt
@Composable
fun QuickFrameLogSection(state: FrameDataTable, onAction: (FrameLoggerActions) -> Unit) {
    var strikeButtonState = remember { mutableStateOf(false) }
    var spareButtonState = remember { mutableStateOf(false) }
    var onePinButtonState = remember { mutableStateOf(false) }
    var twoPinButtonState = remember { mutableStateOf(false) }
    var threePinButtonState = remember { mutableStateOf(false) }
    var fourPinButtonState = remember { mutableStateOf(false) }
    var fivePinButtonState = remember { mutableStateOf(false) }
    var sixPinButtonState = remember { mutableStateOf(false) }
    var sevenPinButtonState = remember { mutableStateOf(false) }
    var eightPinButtonState = remember { mutableStateOf(false) }
    var ninePinButtonState = remember { mutableStateOf(false) }
    var tenPinButtonState = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .height(250.dp)
    ) {
        Column(
            modifier = Modifier
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
                Row(
                    modifier = Modifier
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "7",
                                                    sevenPinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "8",
                                                    eightPinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "9",
                                                    ninePinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "10",
                                                    tenPinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "4",
                                                    fourPinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "5",
                                                    fivePinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "6",
                                                    sixPinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "2",
                                                    twoPinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "3",
                                                    threePinButtonState.value
                                                )
                                            )
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
                                            onAction(
                                                FrameLoggerActions.TappedPin(
                                                    "1",
                                                    onePinButtonState.value
                                                )
                                            )
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
                                    onAction(
                                        FrameLoggerActions.TappedMark(
                                            "spare",
                                            spareButtonState.value
                                        )
                                    )
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
                                    onAction(
                                        FrameLoggerActions.TappedMark(
                                            "strike",
                                            strikeButtonState.value
                                        )
                                    )
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