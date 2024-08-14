package com.dlrjsgml.doparich.feature.main.board.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.ui.theme.title1

@Composable
fun DetailScreen(
    id: Long,
    navController : NavHostController,
    viewModel: DetailViewModel = viewModel(),
){
    LaunchedEffect(Unit) {
        viewModel.getBoard(id)
    }
    val uiState by viewModel.uiState.collectAsState()
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "${uiState.id}",
            style = title1
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "${uiState.content}",
            style = title1
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "${uiState.writer}",
                style = title1
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "${uiState.likes}",
                style = title1
            )
        }


    }



}