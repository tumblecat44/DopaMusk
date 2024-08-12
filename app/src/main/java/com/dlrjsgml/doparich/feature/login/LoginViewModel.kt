package com.dlrjsgml.doparich.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.account.login.UserDTO
import com.dlrjsgml.doparich.data.datastore.ImplRepository
import com.dlrjsgml.doparich.data.datastore.UserId
import com.dlrjsgml.doparich.remote.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class SingInState(
    val id: String = "",
    val pw: String = "",
)

sealed interface SignInSideEffect {
    data object LoginSuccess : SignInSideEffect
    data object LoginFailure : SignInSideEffect
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val implRepository: ImplRepository
): ViewModel() {

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
                saveId()
                getId()

            } catch (_: Exception) {
                Log.d("Login Check", "오류");
                _uiEffect.emit(SignInSideEffect.LoginFailure)
            }
        }
    }

    fun saveId() {
        viewModelScope.launch(Dispatchers.IO) {
            implRepository.saveUserId(
                UserId(
                    id = uiState.value.id
                )
            )
        }
    }
    suspend fun getId(): String {
        return withContext(Dispatchers.IO) {
            // Use firstOrNull to get the first emitted value
            val userId = implRepository.getUserId().firstOrNull()?.id ?: "null"
            userId
        }
    }


    fun updateId(id: String) {
        _uiState.update { it.copy(id = id) }
    }

    fun updatePw(pw: String) {
        _uiState.update { it.copy(pw = pw) }
    }
}