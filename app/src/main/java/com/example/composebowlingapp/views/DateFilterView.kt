package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameLoggerActions

enum class DateType {
    TODAY, ALL, RANGE
}

@Composable
fun DateFilter(onAction: (FrameLoggerActions) -> Unit) {
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
                        .weight(2f)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 15.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .align(Alignment.CenterVertically)
                                .weight(1f),
                            onClick =
                            {
                                onAction(FrameLoggerActions.DateFilterChanged(DateType.RANGE))
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    "Filter",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                        TextButton(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .align(Alignment.CenterVertically)
                                .weight(1f),
                            onClick =
                            {
                                onAction(FrameLoggerActions.DateFilterChanged(DateType.ALL))
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    "All",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                        TextButton(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .align(Alignment.CenterVertically)
                                .weight(1f),
                            onClick =
                            {
                                onAction(FrameLoggerActions.DateFilterChanged(DateType.TODAY))
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    "Today",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}