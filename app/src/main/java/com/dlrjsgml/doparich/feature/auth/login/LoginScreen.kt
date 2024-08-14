package com.dlrjsgml.doparich.feature.auth.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
//import com.dlrjsgml.doparich.data.info.UserRepository
import com.dlrjsgml.doparich.root.NavGroup
import com.dlrjsgml.doparich.ui.component.MyTextField
import com.dlrjsgml.doparich.ui.theme.caption2
import com.dlrjsgml.doparich.ui.theme.title1


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(
    navBottomVisible: (Boolean) -> Unit,
    navController: NavHostController,

    viewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
//    val userRepository = UserRepository(context)

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is SignInSideEffect.LoginSuccess -> {
                    navController.navigate(NavGroup.HOME)

                }

                SignInSideEffect.LoginFailure -> {
                    Toast.makeText(context, "로그인 실패.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column {
        Text(
            modifier = Modifier.padding(top = 70.dp, start = 20.dp), text = "로그인", style = title1
        )
        MyTextField(
            modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
            hint = "아이디",
            value = uiState.id,
            onValueChange = viewModel::updateId,
        )
        MyTextField(
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(horizontal = 20.dp),
            value = uiState.pw,
            hint = "비밀번호",
            secured = true,
            onValueChange = viewModel::updatePw
        )
        Spacer(modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 20.dp))
        Spacer(modifier = Modifier.weight(1f))
        LaunchedEffect(key1 = true) {
            navBottomVisible(false)
        }
        Button(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            onClick = {
                if (uiState.id.isNotEmpty() && uiState.pw.isNotEmpty()) {
                    viewModel.login()
                } else {
                    Toast.makeText(context, "모두 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "시작하기")
        }
        TextButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { navController.navigate(NavGroup.ACCOUNT) }) {
            Text(text = "회원가입", style = caption2)
        }

        Spacer(modifier = Modifier.height(10.dp))

    }

}

