package com.dlrjsgml.doparich.feature.main.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.datastore.repository.ImplRepository
import com.dlrjsgml.doparich.data.datastore.model.UserId
import com.dlrjsgml.doparich.remote.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


data class MyInfoState(
    val name: String = "",
    val id: String = "",
)

@HiltViewModel
class MyViewModel @Inject constructor(
    private val implRepository: ImplRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyInfoState())
    val uiState = _uiState.asStateFlow()


    fun getUserInfos() {
        viewModelScope.launch {
            try {
                val id = getId()
                val myUserInfoResponse = RetrofitClient.myService.userInfo(id)
                _uiState.update {
                    it.copy(
                        name = myUserInfoResponse.userName, id = myUserInfoResponse.userId
                    )
                }

            } catch (e: Exception) {
                _uiState.update { it.copy(name = "null") }

            }


        }
    }

    fun saveIdReSet() {
        viewModelScope.launch(Dispatchers.IO) {
            implRepository.saveUserId(
                UserId(
                    id = "null"
                )
            )
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