package com.dlrjsgml.doparich.ui.component

import android.graphics.Color
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dlrjsgml.doparich.ui.theme.White

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimatedButton() {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 1f else 2f)

    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {  },
            modifier = Modifier
                .scale(scale.value)
                .height(40.dp)
                .width(200.dp)
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selected.value = true }

                        MotionEvent.ACTION_UP  -> {
                            selected.value = false }
                    }
                    true
                }
        ) {
            Text(text = "Explore Airbnb", fontSize = 15.sp, color = White)
        }
    }
}