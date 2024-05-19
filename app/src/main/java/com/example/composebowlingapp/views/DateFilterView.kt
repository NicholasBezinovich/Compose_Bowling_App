package com.example.composebowlingapp.views

import android.widget.GridView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
fun DateFilter(listOfFilter: List<String>, appliedFilters: List<String>, dateType: DateType, onAction: (FrameLoggerActions) -> Unit) {
    var dark = isSystemInDarkTheme()
    var textColor = if (dark) {Color.White} else {Color.Black}
    var backgroundColor = if (dark) {Color.Black} else {Color.White}
    var listOfDateTypes = arrayListOf(DateType.TODAY, DateType.ALL, DateType.RANGE)
    var showDropDown = remember {mutableStateOf(false)}
    var showFilterDropDown = remember {mutableStateOf(false)}
    var addFilterValue = remember {mutableStateOf("")}

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
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
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
                            AlertDialog(
                                onDismissRequest =
                                {
                                    showFilterDropDown.value = false
                                },
                                buttons =
                                {
                                    Box(
                                        modifier = Modifier
                                            .background(textColor)
                                            .padding(15.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(14.dp),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Text("Add & Apply Filters", color = backgroundColor)

                                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                                TextField(
                                                    modifier = Modifier
                                                        .border(width = 1.dp, Color.Black)
                                                        .weight(2f),
                                                    value = addFilterValue.value,
                                                    onValueChange = {addFilterValue.value = it},
                                                    placeholder = {"Add Filter"},
                                                    textStyle = TextStyle(color = backgroundColor)
                                                )
                                                TextButton(
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .background(backgroundColor)
                                                        .align(Alignment.CenterVertically)
                                                        .clip(RoundedCornerShape(5.dp)),
                                                    onClick =
                                                    {
                                                        if (addFilterValue.value.count() > 0 &&
                                                            !appliedFilters.contains(addFilterValue.value)) {
                                                            onAction(
                                                                FrameLoggerActions.AddToFilterList(
                                                                    addFilterValue.value
                                                                )
                                                            )
                                                            addFilterValue.value = ""
                                                        }
                                                    }
                                                ) {
                                                    Box {
                                                        Text(
                                                            "Add",
                                                            color = textColor,
                                                            fontSize = 15.sp,
                                                            modifier = Modifier
                                                                .align(Alignment.Center)
                                                                .padding(5.dp)
                                                        )
                                                    }
                                                }
                                            }
                                            Row(
                                                modifier = Modifier
                                                    .horizontalScroll(rememberScrollState()),
                                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                                            ) {
                                                listOfFilter.forEach {
                                                    if (it.count() > 0) {
                                                        AddRemoveFilterBubble(
                                                            appliedFilters = appliedFilters,
                                                            title = it,
                                                            Action = onAction
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            )
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
