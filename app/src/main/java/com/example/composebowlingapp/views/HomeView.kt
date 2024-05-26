package com.example.composebowlingapp.views

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composebowlingapp.FrameDataViewModel
import com.example.composebowlingapp.components.DateFilter
import com.example.composebowlingapp.components.FilterBubble
import com.example.composebowlingapp.components.FramesLoggedList
import com.example.composebowlingapp.components.ProfileSelectionView
import com.example.composebowlingapp.components.QuickFrameLogSection
import com.example.composebowlingapp.components.QuickGameScoreLogged
import com.example.composebowlingapp.components.StatsSection

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