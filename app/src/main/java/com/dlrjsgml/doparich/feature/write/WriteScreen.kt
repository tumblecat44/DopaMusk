package com.dlrjsgml.doparich.feature.write

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.ui.component.MyTextField
import com.dlrjsgml.doparich.ui.theme.title1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WriteScreen(navController: NavHostController,
    viewModel: WriteViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect{
            when (it) {
                is WriteSideEffect.WriteSuccess -> {
                    Toast.makeText(context, "글작성 성공~!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()

                }
                WriteSideEffect.WriteFailure -> Toast.makeText(context, "로그인 실패.", Toast.LENGTH_SHORT).show()


            }
        }
    }
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        Text(modifier = Modifier.padding(start = 20.dp), text = "글 작성", style = title1)
        Spacer(modifier = Modifier.height(10.dp))
        MyTextField(singleLine = false,
            hint = "여기에 글을 작성해주세요" +
                    "ㅇㅇㅇ\n ㅇㅇㅇㅇㅇㅇ\n ㅇㅇㅁㄴㅁㅇㅁㅇㄴ",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(300.dp),
            value = uiState.content, onValueChange = viewModel::updateContent)
        Button(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            onClick = {
                CoroutineScope(Dispatchers.IO).launch{
                    viewModel.uploadContent()
                }
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "글 작성완료")
        }
    }

}

