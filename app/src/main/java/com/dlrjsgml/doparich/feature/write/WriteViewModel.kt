package com.dlrjsgml.doparich.feature.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.datastore.ImplRepository
import com.dlrjsgml.doparich.data.home.boardlist.write.WriteDTO
import com.dlrjsgml.doparich.feature.login.SignInSideEffect
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

data class WriteState(
    val content : String ="",
    val writer : String ="이건희"
)
sealed interface WriteSideEffect {
    data object WriteSuccess : WriteSideEffect
    data object WriteFailure : WriteSideEffect
}

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val implRepository: ImplRepository
) : ViewModel() {

    private val _uiEffect = MutableSharedFlow<WriteSideEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(WriteState())
    val uiState = _uiState.asStateFlow()

    suspend fun uploadContent(){
        val writeContent = uiState.value.content
        val writeWriter = getId()

        viewModelScope.launch {
            try{
                val writeContents = WriteDTO(writeContent,writeWriter)
                val myWriteResponse = RetrofitClient.writeService.loginUser(writeContents)
                _uiEffect.emit(WriteSideEffect.WriteSuccess)
            } catch (e:Exception){
                _uiEffect.emit(WriteSideEffect.WriteFailure)
            }

        }
    }
    suspend fun getId(): String {
        return withContext(Dispatchers.IO) {
            // Use firstOrNull to get the first emitted value
            val userId = implRepository.getUserId().firstOrNull()?.id ?: "null"
            userId
        }
    }

    fun updateContent(content: String){
        _uiState.update { it.copy(content = content) }
    }

}
