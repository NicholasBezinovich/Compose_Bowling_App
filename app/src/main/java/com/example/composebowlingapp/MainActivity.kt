package com.example.composebowlingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.composebowlingapp.ui.theme.ComposeBowlingAppTheme
import com.example.composebowlingapp.views.DateFilter
import com.example.composebowlingapp.views.DateType
import com.example.composebowlingapp.views.FramesLoggedList
import com.example.composebowlingapp.views.ProfileSelectionView
import com.example.composebowlingapp.views.QuickFrameLogSection
import com.example.composebowlingapp.views.QuickGameScoreLogged
import com.example.composebowlingapp.views.StatsSection

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
                val state = viewModel.state
                val gameDataListState = viewModel.listOfGames

                Box(modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(modifier = Modifier.defaultMinSize(minWidth = Dp.Infinity, minHeight = 70.dp)) {
                            Box(modifier = Modifier.weight(1f)) {
                                ProfileSelectionView(
                                    viewModel = viewModel,
                                    onAction = viewModel::onAction
                                )
                            }
                            Box(modifier = Modifier.weight(2f)) {
                                DateFilter(onAction = viewModel::onAction)
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
                        FramesLoggedList(frameList = viewModel.getFilteredList(), gameList = gameDataListState, onAction = viewModel::onAction, viewModel = viewModel)
                    }
                }
            }
        }
    }
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
