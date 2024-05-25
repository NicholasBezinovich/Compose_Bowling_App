package com.example.composebowlingapp

import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.composebowlingapp.ui.theme.ComposeBowlingAppTheme
import com.example.composebowlingapp.views.DateFilter
import com.example.composebowlingapp.views.DateType
import com.example.composebowlingapp.views.FilterBubble
import com.example.composebowlingapp.views.FramesLoggedList
import com.example.composebowlingapp.views.ProfileSelectionView
import com.example.composebowlingapp.views.QuickFrameLogSection
import com.example.composebowlingapp.views.QuickGameScoreLogged
import com.example.composebowlingapp.views.StatsSection
import java.lang.Long
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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

    @OptIn(ExperimentalMaterial3Api::class)
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

                val dateRangePickerState = rememberDateRangePickerState()
                val tabSelected = remember {mutableStateOf("HOME")}

                Box(modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column {
                        Column (
                            modifier = Modifier
                                .background(Color.LightGray)
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .weight(10f),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            if (tabSelected.value == "HOME") {
                                HomeView(viewModel)
                            } else {
                                Row(modifier = Modifier.defaultMinSize(minWidth = Dp.Infinity, minHeight = 70.dp)) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        ProfileSelectionView(
                                            viewModel = viewModel,
                                            onAction = viewModel::onAction
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Box(modifier = Modifier.weight(2f)) {
                                        DateFilter(
                                            dateFrom = viewModel.dateFrom.value,
                                            dateTo = viewModel.dateTo.value,
                                            listOfFilter = viewModel.returnListOfFilters(),
                                            appliedFilters = viewModel.returnAppliedListOfFilters(),
                                            dateType = viewModel.dateType.value,
                                            onAction = viewModel::onAction)
                                    }
                                }
                            }
                            if (viewModel.showDatePicker.value) {
                                DatePickerDialog(
                                    onDismissRequest =
                                    {
                                        if (dateRangePickerState.selectedStartDateMillis != null &&
                                            dateRangePickerState.selectedEndDateMillis != null
                                        ) {
                                            println(
                                                "Date From: " + viewModel.convertMillisToLocalDate(
                                                    millis = dateRangePickerState.selectedStartDateMillis!!
                                                )
                                            )
                                            println(
                                                "Date To: " + viewModel.convertMillisToLocalDate(
                                                    millis = dateRangePickerState.selectedEndDateMillis!!
                                                )
                                            )
                                            viewModel.dateFromDF.value =
                                                viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedStartDateMillis!!)
                                            viewModel.dateToDF.value =
                                                viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedEndDateMillis!!)
                                            viewModel.dateFrom.value =
                                                viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedStartDateMillis!!)
                                                    .toString()
                                            viewModel.dateTo.value =
                                                viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedEndDateMillis!!)
                                                    .toString()
                                            viewModel.setStatistics()
                                        }
                                        viewModel.showDatePicker.value = false
                                    },
                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                viewModel.onAction(
                                                    FrameLoggerActions.DateFilterChanged(
                                                        dateType = DateType.RANGE
                                                    )
                                                )
                                                if (dateRangePickerState.selectedStartDateMillis != null &&
                                                    dateRangePickerState.selectedEndDateMillis != null
                                                ) {
                                                    println(
                                                        "Date From: " + viewModel.convertMillisToLocalDate(
                                                            millis = dateRangePickerState.selectedStartDateMillis!!
                                                        )
                                                    )
                                                    println(
                                                        "Date To: " + viewModel.convertMillisToLocalDate(
                                                            millis = dateRangePickerState.selectedEndDateMillis!!
                                                        )
                                                    )
                                                    viewModel.dateFromDF.value =
                                                        viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedStartDateMillis!!)
                                                    viewModel.dateToDF.value =
                                                        viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedEndDateMillis!!)
                                                    viewModel.dateFrom.value =
                                                        viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedStartDateMillis!!)
                                                            .toString()
                                                    viewModel.dateTo.value =
                                                        viewModel.convertMillisToLocalDate(millis = dateRangePickerState.selectedEndDateMillis!!)
                                                            .toString()
                                                    viewModel.setStatistics()
                                                }
                                            }
                                        ) {
                                            Text(
                                                "Confirm"
                                            )
                                        }
                                    },
                                    dismissButton = {
                                        TextButton(
                                            onClick = {
                                                viewModel.onAction(
                                                    FrameLoggerActions.DateFilterChanged(
                                                        dateType = DateType.RANGE
                                                    )
                                                )
                                            }
                                        ) {
                                            Text(
                                                "Dismiss"
                                            )
                                        }
                                    }
                                ) {
                                    DateRangePicker(state = dateRangePickerState)
                                }
                            }
                        }
                        //Bottom Tab Bar
                        BottomAppBar(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            TextButton(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    tabSelected.value = "HOME"
                                }) {
                                Text("HOME")
                            }
                            TextButton(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    tabSelected.value = "ENTER GAME"
                                }) {
                                Text("ENTER GAME")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HomeView(viewModel: FrameDataViewModel) {

    val state = viewModel.state
    Row(modifier = Modifier.defaultMinSize(minWidth = Dp.Infinity, minHeight = 70.dp)) {
        Box(modifier = Modifier.weight(1f)) {
            ProfileSelectionView(
                viewModel = viewModel,
                onAction = viewModel::onAction
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(modifier = Modifier.weight(2f)) {
            DateFilter(
                dateFrom = viewModel.dateFrom.value,
                dateTo = viewModel.dateTo.value,
                listOfFilter = viewModel.returnListOfFilters(),
                appliedFilters = viewModel.returnAppliedListOfFilters(),
                dateType = viewModel.dateType.value,
                onAction = viewModel::onAction)
        }
    }

    if (viewModel.appliedFilters.value != "") {
        Row(
            modifier = Modifier
                .padding(15.dp, 5.dp, 15.dp, 5.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            viewModel.returnAppliedListOfFilters().forEach {
                FilterBubble(it, deleteAction = viewModel::onAction)
            }
        }
    }
    StatsSection(
        strikePercent = viewModel.strikePercent.value,
        sparePercent = viewModel.sparePercent.value,
        openPercent = viewModel.openPercent.value,
        averageScore = viewModel.averageGame.value
    )
    QuickFrameLogSection(state = state, onAction = viewModel::onAction)
    QuickGameScoreLogged(onAction = viewModel::onAction)
    FramesLoggedList(frameList = viewModel.getFilteredList(), gameList = viewModel.filteredGameList(), onAction = viewModel::onAction, viewModel = viewModel)
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeBowlingAppTheme {
        var frameDataList = mutableListOf<FrameDataTable>()
        frameDataList.add(FrameDataTable())

        var gameDateList = mutableListOf<GameDataTable>()
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                //DateFilter()
                StatsSection(
                    strikePercent = 0.0,
                    sparePercent = 0.0,
                    openPercent = 0.0,
                    averageScore = 0.0
                )
                //QuickFrameLogSection(state = state, onAction = viewModel::onAction)
                //QuickGameScoreLogged(gameState = gameState, onAction = viewModel::onAction)
                //FramesLoggedList(frameList = frameDataList, gameList = gameDateList)
            }
        }
    }
}
