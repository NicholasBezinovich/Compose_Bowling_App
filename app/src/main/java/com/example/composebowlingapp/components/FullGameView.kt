package com.example.composebowlingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebowlingapp.GameLoggerViewActions
import com.example.composebowlingapp.views.FullGameLoggerView

@Composable
fun FullGameView(name: String, game: MutableList<String>, onAction: (GameLoggerViewActions) -> Unit) {
    Column {
        Row {
            Button(
                onClick = {}, modifier = Modifier
                    .padding(5.dp)
            ) {
                Text("Profile: " + name)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {}, modifier = Modifier
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        end = 5.dp
                    )
            ) {
                Text("Tags")
            }
        }
        Row {
            game.forEach() {
                Row(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .border(1.dp, Color.Black)
                            .weight(1f)
                    ) {
                        Column() {
                            Row {
                                Text(
                                    "",
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
                                            "",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                            Row {
                                Text(
                                    "",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {}, modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 5.dp
                        )
                ) {
                    Text("<")
                }
                Button(
                    onClick = {}, modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 5.dp
                        )
                ) {
                    Text(">")
                }
            if (false) {
                Button(
                    onClick = {}, modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 5.dp
                        )
                ) {
                    Text("Submit")
                }
            }
        }
    }

}

@Preview
@Composable
fun FGVPreview() {
    FullGameView(
        name = "Nick",
        game = mutableListOf("", "", "", "", "", "", "", "", "", ""),
        onAction = {}
    )
}