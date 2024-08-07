package com.dlrjsgml.doparich.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.login.UserDTO
import com.dlrjsgml.doparich.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class SingInState(
    val id: String = "",
    val pw: String = "",
)

sealed interface SignInSideEffect {
    data object LoginSuccess : SignInSideEffect
    data object LoginFailure : SignInSideEffect
}

class LoginViewModel : ViewModel() {

    private val _uiEffect = MutableSharedFlow<SignInSideEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(SingInState())
    val uiState = _uiState.asStateFlow()


    fun login() {
        val id = uiState.value.id
        val pw = uiState.value.pw

        viewModelScope.launch {
            try {
                val user = UserDTO(id, pw)
                val myAuthResponse = RetrofitClient.userService.loginUser(user)
                val effect = SignInSideEffect.LoginSuccess
                _uiEffect.emit(effect)
            } catch (_: Exception) {
                Log.d("Login Check", "오류");
                _uiEffect.emit(SignInSideEffect.LoginFailure)
            }
        }
    }

    fun upLoadData() {
        val id = uiState.value.id
        viewModelScope.launch {
            try {
                val userId = id
                val usersInfo = RetrofitClient.infoService.getUserInfo(userId)
                Log.d("이", "$usersInfo");


            } catch (_: Exception) {
                Log.d("이", "dlrjsgml44 no Ok");
            }
        }
    }



    fun updateId(id: String) {
        _uiState.update { it.copy(id = id) }
    }

    fun updatePw(pw: String) {
        _uiState.update { it.copy(pw = pw) }
    }
}