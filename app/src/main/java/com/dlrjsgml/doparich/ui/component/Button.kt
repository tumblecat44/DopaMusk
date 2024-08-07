package com.dlrjsgml.doparich.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


sealed class ButtonType(
    val contentPadding: PaddingValues,
    val shape: RoundedCornerShape
) {
    data object Large : ButtonType(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        shape = RoundedCornerShape(10.dp)
    )

    data object Medium : ButtonType(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
        shape = RoundedCornerShape(8.dp)
    )

    data object Small : ButtonType(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
        shape = RoundedCornerShape(6.dp)
    )
}
@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    text : String,
    type : ButtonType,
    enabled  : Boolean,
){

    Box(){

    }
}