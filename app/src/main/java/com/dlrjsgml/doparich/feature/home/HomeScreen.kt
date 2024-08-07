package com.dlrjsgml.doparich.feature.home

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.backhandler.HomeBackOnPressed
import com.dlrjsgml.doparich.feature.login.LoginViewModel
import com.dlrjsgml.doparich.remote.RetrofitClient
import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.doparich.ui.theme.content1
import com.dlrjsgml.doparich.ui.theme.content2
import com.dlrjsgml.doparich.ui.theme.title1
import com.dlrjsgml.doparich.ui.theme.title2


@Composable
fun HomeScreen(
    navBottomVisible: (Boolean) -> Unit,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),

    ) {
//    val uiMainState by mainViewModel.uiState.collectAsState()
    viewModel.getBoardContents()
    val uiState by viewModel.uiState.collectAsState()

    Log.d("글", "dlrjsgml44 ${uiState.boards}");
    Box(modifier = Modifier.fillMaxSize()){
        HomeBackOnPressed()
        LaunchedEffect(key1 = true) {
            navBottomVisible(true)
        }
        Column() {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "최신글",
                style = title1
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(uiState.boards.size){
                    val item = uiState.boards[it]
                    ContentCards(writer = item.writer, content = item.content)

                }
            }
        }

        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(20.dp)
            .height(45.dp)){
            BoardWriter(onClick = {navController.navigate(NavGroup.WRITE)}, text = "글쓰기")
        }
    }



}


@Composable
fun ContentCards(writer: String,content : String) {
    Box{
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp)){
            Text(text = writer, style = content2)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = content, style = content1)
        }

    }

}
@Preview
@Composable
fun adfad(){
    ContentCards(writer = "이건희", content = "안녕하세욧")
}


@Composable
fun BoardWriter(onClick:() -> Unit, text : String){
    ExtendedFloatingActionButton(
        onClick ={onClick()},
        icon ={Icon(Icons.Filled.Edit, "Extended floating action buttn.")},
        text = { Text(text = text)}
    )
}
