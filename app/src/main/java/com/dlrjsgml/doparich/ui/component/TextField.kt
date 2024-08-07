package com.dlrjsgml.doparich.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dlrjsgml.doparich.R
import com.dlrjsgml.doparich.ui.theme.Blue800
import com.dlrjsgml.doparich.ui.theme.Gray600
import com.dlrjsgml.doparich.ui.theme.content1

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint : String = "",
    enabled:Boolean = true,
    secured: Boolean = false,
    singleLine : Boolean = true
) {
    var isFocused by remember { mutableStateOf(false) }
    val showText by remember {
        mutableStateOf(false)
    }
    val isSecured = secured && !showText
    val icon = if (!secured) {
        R.drawable.ic_cross_circle
    } else if (showText) {
        R.drawable.id_eye_show
    } else {
        R.drawable.ic_eye_hide
    }
    val isHint = value.isEmpty()


    Box{

        BasicTextField(modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 2.dp,
                color = Blue800,
                shape = RoundedCornerShape(12.dp)
            ), value = value, onValueChange = onValueChange,
            textStyle = content1,
            keyboardOptions = if (secured) KeyboardOptions.Default else KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (secured) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = singleLine,
            decorationBox = { innerTextField ->
                Box(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    innerTextField()
                }


            })
        if(isHint){
            Text(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp), text = hint, style = content1, color = Gray600)
        }

    }


}