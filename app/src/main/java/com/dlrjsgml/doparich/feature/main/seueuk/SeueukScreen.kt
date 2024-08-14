package com.dlrjsgml.doparich.feature.main.seueuk

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.backhandler.BackHandlers

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoardScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    VerticalPager(state = pagerState) { page ->
        // Our page content
        Text(
            text = "Page: $page",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun CustomViewPager(pagerState: PagerState) {
//    VerticalPager(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        state = pagerState
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp)
//                .padding(horizontal = 20.dp),
//            shape = RoundedCornerShape(8.dp),
//            border = BorderStroke(1.dp, Color.Black),
//            colors = CardDefaults.cardColors(
//                containerColor = Color.White,
//            ),
//            elevation = CardDefaults.cardElevation(10.dp),
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center, // 세로 방향으로 가운데 정렬
//                horizontalAlignment = Alignment.CenterHorizontally // 가로 방향으로 가운데 정렬
//            ) {
//                Text(
//                    text = "Page $it",
//                )
//            }
//        }
//    }
//}
