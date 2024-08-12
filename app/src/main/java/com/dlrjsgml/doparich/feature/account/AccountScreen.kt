package com.dlrjsgml.doparich.feature.account

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dlrjsgml.doparich.feature.login.SignInSideEffect

import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.doparich.ui.component.MyTextField
import com.dlrjsgml.doparich.ui.theme.caption2
import com.dlrjsgml.doparich.ui.theme.title1

@Composable
fun AccountScreen(
    navController: NavHostController,
    viewModel: AccountViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is AccountEffect.AccountSuccess -> {
                    Toast.makeText(context, "가입성공", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }

                AccountEffect.AccountFailure -> {
                    Toast.makeText(context, "가입실패!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column {
        Text(
            modifier = Modifier.padding(top = 70.dp, start = 20.dp), text = "회원가입", style = title1
        )

        MyTextField(
            modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
            value = uiState.name,
            hint = "이름",
            onValueChange = viewModel::updateName,
        )

        MyTextField(
            modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 20.dp),
            value = uiState.id,
            hint = "아이디",
            onValueChange = viewModel::updateId,
        )

        MyTextField(
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(horizontal = 20.dp),
            value = uiState.pw,
            secured = true,
            hint = "비밀번호",
            onValueChange = viewModel::updatePw
        )

        Spacer(modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 20.dp))
        Button(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            onClick = {
                if (uiState.name.isNotEmpty() && uiState.id.isNotEmpty() && uiState.pw.isNotEmpty()) {
                    viewModel.account()
                } else {
                    Toast.makeText(context, "모두 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "회원가입")
        }
    }

}