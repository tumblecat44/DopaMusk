package com.dlrjsgml.doparich.feature.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.home.boardlist.write.WriteDTO
import com.dlrjsgml.doparich.feature.login.SignInSideEffect
import com.dlrjsgml.doparich.remote.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
class WriteViewModel @Inject constructor() : ViewModel() {

    private val _uiEffect = MutableSharedFlow<WriteSideEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(WriteState())
    val uiState = _uiState.asStateFlow()

    fun uploadContent(){
        val writeContent = uiState.value.content
        val writeWriter = uiState.value.writer

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

    fun updateContent(content: String){
        _uiState.update { it.copy(content = content) }
    }

}
