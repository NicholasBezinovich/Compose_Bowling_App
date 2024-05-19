package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composebowlingapp.FrameDataViewModel
import com.example.composebowlingapp.FrameLoggerActions

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileSelectionView(viewModel: FrameDataViewModel, onAction: (FrameLoggerActions) -> Unit) {
    var showDialog = remember { mutableStateOf( false) }
    var profileName = remember { mutableStateOf("") }
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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 50.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            showDialog.value = true
                        }
                    ) {
                        if (showDialog.value) {
                            AlertDialog(
                                onDismissRequest =
                                {
                                    showDialog.value = false
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
                                            verticalArrangement = Arrangement.spacedBy(10.dp),
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                "Add Profile Name",
                                                modifier = Modifier.padding(5.dp)
                                            )
                                            TextField(
                                                value = profileName.value,
                                                onValueChange = { profileName.value = it },
                                                label = { Text("Profile name") },
                                                modifier = Modifier.padding(5.dp)
                                            )
                                            TextButton(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(5.dp)
                                                    .clip(CircleShape)
                                                    .background(Color.DarkGray),
                                                onClick =
                                                {
                                                    if (profileName.value.count() != 0) {
                                                        viewModel.profiles.add(profileName.value)
                                                        profileName.value = ""
                                                        showDialog.value = false
                                                    }
                                                }
                                            ) {
                                                Text(
                                                    "Confirm",
                                                    color = Color.White,
                                                    fontSize = 10.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                        Column(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "+",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(10.dp, 5.dp, 5.dp, 5.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                ) {
                    Card(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 120.dp, minHeight = 50.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onAction(FrameLoggerActions.ProfileToggled(b = true))
                        }
                    ) {
                        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
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
                                    if (it != viewModel.profile.value) {
                                        Divider(
                                            modifier = Modifier
                                                .height(1.dp)
                                                .background(Color.Gray)
                                        )
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(50.dp)
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
}

/*@Preview
@Composable
fun ProfilePreview() {
    ProfileSelectionView()
}*/