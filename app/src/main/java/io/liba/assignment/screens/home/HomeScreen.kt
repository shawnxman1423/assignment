package io.liba.assignment.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding
import io.liba.assignment.common.parseColor
import io.liba.assignment.screens.ui.theme.round
import io.liba.assignment.superdoMarket.models.GroceryItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val state by viewModel.viewState.collectAsState()
    val groceries = state.groceries

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            HomeTopBar(isActive = state.isActive,
                onStart = { viewModel.start() },
                onStop = { viewModel.stop() })
        },
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = state.weightFilter,
                    onValueChange = { text -> viewModel.setWeightFilter(text) },
                    label = { Text("Weight Filter") })
                Spacer(modifier = Modifier.height(16.dp))
                Divider()

                LazyColumn {
                    items(groceries) { item ->
                        Column {
                            Divider()
                            Spacer(modifier = Modifier.height(6.dp))
                            GroceryItemCard(item = item)
                            Spacer(modifier = Modifier.height(6.dp))
                            Divider()
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeTopBar(
    isActive: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit,
) {
    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Incoming Item Feed")
            Spacer(modifier = Modifier.weight(1f))
            when (isActive) {
                true -> OutlinedButton(onClick = onStop, content = { Text(text = "Stop") })
                false -> OutlinedButton(onClick = onStart, content = { Text(text = "Start") })
            }
        }
    }
}

@Composable
fun GroceryItemCard(item: GroceryItem) {
    Row(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier
            .border(shape = round, border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)))
            .padding(2.dp)
            .size(56.dp)
            .clip(round)
            .background(Color(parseColor(item.bagColor))))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(item.name)
            Spacer(modifier = Modifier.height(6.dp))
            Text(item.weight)
        }
    }
}
