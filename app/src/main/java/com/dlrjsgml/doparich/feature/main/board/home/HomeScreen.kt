package com.dlrjsgml.doparich.feature.main.board.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.R
import com.dlrjsgml.doparich.backhandler.HomeBackOnPressed
import com.dlrjsgml.doparich.data.home.boardlist.board.BoardContentResponse
import com.dlrjsgml.doparich.feature.main.board.sub.error.ErrorScreen
import com.dlrjsgml.doparich.feature.main.board.sub.loading.LoadingScreen
import com.dlrjsgml.doparich.feature.auth.login.SignInSideEffect
import com.dlrjsgml.doparich.remote.RetrofitClient
import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.doparich.ui.component.MyTextField
import com.dlrjsgml.doparich.ui.theme.Blue700
import com.dlrjsgml.doparich.ui.theme.Gray200
import com.dlrjsgml.doparich.ui.theme.Gray800
import com.dlrjsgml.doparich.ui.theme.caption3
import com.dlrjsgml.doparich.ui.theme.caption3Bold
import com.dlrjsgml.doparich.ui.theme.content0
import com.dlrjsgml.doparich.ui.theme.title1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navBottomVisible: (Boolean) -> Unit,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    var contentToShow by remember { mutableStateOf<@Composable () -> Unit>({}) }

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }
    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is BoardSideEffect.BoardLoading -> {
                    contentToShow = { LoadingScreen() }
                    Log.d("확인", "Loadinging");
                }

                is BoardSideEffect.BoardSuccess -> {
                    contentToShow = { BoardList(boardList = uiState.boards, navController) }
                }

                is BoardSideEffect.BoardFailed -> {
                    contentToShow = { ErrorScreen() }
                }
            }
        }
    }


//    val uiMainState by mainViewModel.uiState.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = uiState.isRefresh,
            onRefresh = { viewModel.refresh() })



    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        HomeBackOnPressed()
        LaunchedEffect(key1 = true) {
            navBottomVisible(true)
        }
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp), text = "최신글", style = title1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.pullRefresh(pullRefreshState),
                contentAlignment = Alignment.TopCenter
            ) {
                contentToShow()
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .height(45.dp)
        ) {
            BoardWriter(onClick = {
                navController.navigate(NavGroup.WRITE)
            }, text = "글쓰기")
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = uiState.isRefresh,
            state = pullRefreshState
        )
    }

}


@Composable
fun ContentCards(
    id: Long,
    writer: String,
    content: String,
    like: Long,
    isLiked : Boolean = false,
    heartClick: () -> Unit,
    onClick: () -> Unit,


) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) { onClick() }
        .padding(horizontal = 10.dp)
        .background(color = Color.White)
        .padding(horizontal = 5.dp)

    ) {
        Text(text = id.toString())
        Text(
            modifier = Modifier
                .padding(top = 27.dp)
                .padding(horizontal = 15.dp),
            text = content,
            style = content0,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Gray200)
        )
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier
                .padding(top = 3.dp, bottom = 23.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = writer,
                style = caption3Bold,
                color = Gray800
            )
            Box(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 20.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { heartClick() },  // Closure는 줄 바꿈을 없애고, 올바르게 닫아줍니다.
                    painter = painterResource(id = R.drawable.ic_like_board),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(if (isLiked) Blue700 else Gray200)  // colorFilter 설정 수정
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = like.toString(),
                    style = caption3
                )
            }

        }
    }

}


@Composable
fun BoardList(
    boardList: List<BoardContentResponse>,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),
) {
    var isSelect by remember {
        mutableStateOf(false)
    }
    LazyColumn {
        items(boardList.size) {
            Spacer(modifier = Modifier.height(8.dp))
            val item = boardList[it]
            ContentCards(
                id = item.id,
                writer = item.writer,
                content = item.content,
                like = item.likes,
                heartClick = {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        RetrofitClient.myLikeUpService.upLikes(id = item.id.toString())
//                    }
                    viewModel.likeBoard(item.id)
                    isSelect = !isSelect
                },
                isLiked =isSelect
            ) {
                navController.navigate("detail/${item.id}")
            }
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}

@Composable
fun BoardWriter(onClick: () -> Unit, text: String) {
    ExtendedFloatingActionButton(onClick = { onClick() },
        icon = { Icon(Icons.Filled.Edit, "Extended floating action buttn.") },
        text = { Text(text = text) })
}
