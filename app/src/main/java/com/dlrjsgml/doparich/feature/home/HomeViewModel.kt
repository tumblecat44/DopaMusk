package com.dlrjsgml.doparich.feature.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.board.BoardContentResponse
import com.dlrjsgml.doparich.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BoardState(
    val boards: List<BoardContentResponse> = emptyList()
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BoardState())
    val uiState = _uiState.asStateFlow()

    fun getBoardContents(){
        viewModelScope.launch {
            try{
                val response = RetrofitClient.boardService.getBoardContents()
                _uiState.update { it.copy(boards = response) }
                Log.d("보드", "dlrjsgml44 $response");
            } catch (e:Exception){
                Log.d("보드", "dlrjsgml44 $e");
            }
        }
    }
}
