package com.dlrjsgml.doparich.feature.main.board.sub.loading

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dlrjsgml.doparich.ui.component.ShimmerItem

@Composable
fun LoadingScreen(){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(20) {
            ShimmerItem(
                isLoading = true,
                contentAfterLoading = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "이 글은 화면에 전부 표시하기엔 너무 긴 글입니다  " +
                                    "그래서 한 줄이 아니라 2줄입니다"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}