package com.dlrjsgml.doparich.feature.my

import android.util.Log
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.datastore.ImplRepository
import com.dlrjsgml.doparich.remote.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


data class MyInfoState(
    val name : String = "",
    val id: String =""
)
@HiltViewModel
class MyViewModel @Inject constructor(
    private val implRepository: ImplRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyInfoState())
    val uiState = _uiState.asStateFlow()


    fun getUserInfos(){
        viewModelScope.launch {
            val id = getId()
            val myUserInfoResponse = RetrofitClient.myService.userInfo(id)
            _uiState.update { it.copy(name = myUserInfoResponse.userName, id = myUserInfoResponse.userId) }


        }
    }
    suspend fun getId(): String {
        return withContext(Dispatchers.IO) {
            // Use firstOrNull to get the first emitted value
            val userId = implRepository.getUserId().firstOrNull()?.id ?: ""
            Log.d("하하", userId)
            userId
        }
    }

}