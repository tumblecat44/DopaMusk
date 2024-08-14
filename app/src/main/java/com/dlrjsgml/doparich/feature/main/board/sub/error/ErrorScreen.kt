package com.dlrjsgml.doparich.feature.main.board.sub.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dlrjsgml.doparich.R
import com.dlrjsgml.doparich.ui.theme.title2

@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Something went wrong", style = title2)
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = R.drawable.sadcat), contentDescription = null)
        }

    }
}

@Preview
@Composable
fun addajkda() {
    ErrorScreen()

}