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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun StatsSection(strikePercent: Double, sparePercent: Double, openPercent: Double, averageScore: Double) {
    Box(
        modifier = Modifier
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
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color.Black)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "Average: " + averageScore.roundToInt(),
                                    modifier = Modifier.align(Alignment.Center)
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
                                        .align(Alignment.Center),
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
                                        .align(Alignment.Center)
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
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "Spares",
                                    modifier = Modifier
                                        .align(Alignment.Center),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "%.2f".format(sparePercent) + "%",
                                    modifier = Modifier
                                        .align(Alignment.Center)
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
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "Opens",
                                    modifier = Modifier
                                        .align(Alignment.Center),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "%.2f".format(openPercent) + "%",
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