package com.example.composebowlingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameLoggerActions

@Composable
fun FilterBubble(title: String, deleteAction: (FrameLoggerActions) -> Unit) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {Color.White} else {Color.Black}
    var backgroundColor = if (dark) {Color.DarkGray} else {Color.White}
    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 15.dp, minHeight = 15.dp)
            .background(backgroundColor, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                title,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 5.dp, 0.dp),
                textAlign = TextAlign.Center,
                color = textColor
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    "X",
                    modifier = Modifier
                        .clickable
                        {
                            deleteAction(FrameLoggerActions.FilterRemoved(title))
                        },
                    textAlign = TextAlign.Center,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun AddRemoveFilterBubble(appliedFilters: List<String>, title: String, Action: (FrameLoggerActions) -> Unit) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {Color.White} else {Color.Black}
    var backgroundColor = if (dark) {Color.DarkGray} else {Color.White}
    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 15.dp, minHeight = 15.dp)
            .background(backgroundColor, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    if (appliedFilters.contains(title)) {
                        //Remove from applied filters list
                        Action(FrameLoggerActions.RemoveFromAppliedFilterList(title))
                    } else {
                        //Add to Applied filters list
                        Action(FrameLoggerActions.AddToAppliedFilterList(title))
                    }
                }
        ) {
            Text(
                title,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 5.dp, 0.dp),
                textAlign = TextAlign.Center,
                color = textColor,
                fontSize = 20.sp
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    if (appliedFilters.contains(title)) {"-"} else {"+"},
                    textAlign = TextAlign.Center,
                    color = textColor,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterBubblePreview() {
    Column {
        FilterBubble(title = "Test", deleteAction = {})
        AddRemoveFilterBubble(appliedFilters = mutableListOf<String>("Test"), title = "Test1") {

        }
    }
}