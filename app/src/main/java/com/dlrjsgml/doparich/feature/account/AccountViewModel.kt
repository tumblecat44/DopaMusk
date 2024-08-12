package com.dlrjsgml.doparich.feature.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.account.newaccount.NewUserDTO
import com.dlrjsgml.doparich.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AccountState(val name: String = "", val id: String = "", val pw: String = "")

sealed interface AccountEffect {
    data object AccountSuccess : AccountEffect
    data object AccountFailure : AccountEffect
}

class AccountViewModel : ViewModel() {
    private val _uiEffect = MutableSharedFlow<AccountEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(AccountState())
    val uiState = _uiState.asStateFlow()


    fun account() {
        val name = uiState.value.name
        val id = uiState.value.id
        val pw = uiState.value.pw

        viewModelScope.launch {
            try {
                val newUserInfo = NewUserDTO(name, id, pw)
                val myNewUserResponse = RetrofitClient.accountService.accountUser(newUserInfo)
                Log.d("New", "$name $id $pw");
                _uiEffect.emit(AccountEffect.AccountSuccess)
            }catch (e : Exception){
                Log.d("로그인", "$e");
                _uiEffect.emit(AccountEffect.AccountFailure)
            }
        }
    }

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }
    fun updateId(id: String) {
        _uiState.update { it.copy(id = id) }
    }

    fun updatePw(pw: String) {
        _uiState.update { it.copy(pw = pw) }
    }

}