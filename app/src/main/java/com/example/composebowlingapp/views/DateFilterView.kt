package com.example.composebowlingapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.composebowlingapp.FrameLoggerActions
import kotlinx.coroutines.launch
import java.util.Date
import androidx.compose.material3.DateRangePicker as DateRangePicker

enum class DateType {
    TODAY, ALL, RANGE
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DateFilter(dateType: DateType, onAction: (FrameLoggerActions) -> Unit) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {Color.White} else {Color.Black}
    var listOfDateTypes = arrayListOf(DateType.TODAY, DateType.ALL, DateType.RANGE)
    var showDropDown = remember {mutableStateOf(false)}
    var showFilterDropDown = remember {mutableStateOf(false)}

    fun getDateTypeText(dt: DateType) : String {
        return if (dt == DateType.ALL) {
            "All"
        } else if (dt == DateType.TODAY) {
            "Today"
        } else {
            "Range"
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
    ) {
        Column {
            Row {
                Card(
                    modifier = Modifier
                        .padding(0.dp, 15.dp, 0.dp, 0.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 15.dp
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                        ) {
                            TextButton(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                onClick =
                                {
                                    showFilterDropDown.value = !showFilterDropDown.value
                                }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        "Filter",
                                        color = textColor,
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                        if (showFilterDropDown.value) {
                            Row {
                                TextButton(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .weight(1f),
                                    onClick =
                                    {
                                        showFilterDropDown.value = !showFilterDropDown.value
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Text(
                                            "Add",
                                            color = textColor,
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
                Card(
                    modifier = Modifier
                        .padding(0.dp, 15.dp, 15.dp, 0.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 15.dp
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                        ) {
                            TextButton(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                onClick =
                                {
                                    showDropDown.value = !showDropDown.value
                                }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        getDateTypeText(dateType),
                                        color = textColor,
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                        if (showDropDown.value) {
                            listOfDateTypes.forEach {
                                if (dateType != it) {
                                    Row {
                                        TextButton(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .weight(1f),
                                            onClick =
                                            {
                                                if (it == DateType.ALL) {
                                                    onAction(
                                                        FrameLoggerActions.DateFilterChanged(
                                                            DateType.ALL
                                                        )
                                                    )
                                                } else if (it == DateType.RANGE) {
                                                    onAction(
                                                        FrameLoggerActions.DateFilterChanged(
                                                            DateType.RANGE
                                                        )
                                                    )
                                                } else {
                                                    onAction(
                                                        FrameLoggerActions.DateFilterChanged(
                                                            DateType.TODAY
                                                        )
                                                    )
                                                }
                                                showDropDown.value = !showDropDown.value
                                            }
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            ) {
                                                Text(
                                                    getDateTypeText(it),
                                                    color = textColor,
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
            }
        }
    }
}
