package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebowlingapp.FrameDataViewModel
import com.example.composebowlingapp.FrameLoggerActions

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileSelectionView(viewModel: FrameDataViewModel, onAction: (FrameLoggerActions) -> Unit) {
    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 120.dp, minHeight = 50.dp)
            .background(Color.LightGray)
            .padding(15.dp, 15.dp, 0.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 120.dp, minHeight = 50.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = Color.White,
                        onClick = {
                            onAction(FrameLoggerActions.ProfileToggled(b = true))
                        }
                    ) {
                        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    "Profile: " + viewModel.profile.value,
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(10.dp, 5.dp, 5.dp, 5.dp)
                                )
                            }
                            if (viewModel.profileTapped.value) {
                                viewModel.profiles.forEach {
                                    Divider(modifier = Modifier.height(1.dp))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                viewModel.profile.value = it
                                                viewModel.setStatistics()
                                                onAction(FrameLoggerActions.ProfileToggled(b = true))
                                            }
                                    ) {
                                        Text(
                                            "Profile: " + it,
                                            fontSize = 15.sp,
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .padding(10.dp, 5.dp, 5.dp, 5.dp)
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
}

/*@Preview
@Composable
fun ProfilePreview() {
    ProfileSelectionView()
}*/