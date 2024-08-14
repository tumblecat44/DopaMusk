package com.dlrjsgml.doparich.feature.main.board.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.home.boardlist.board.BoardContentResponse
import com.dlrjsgml.doparich.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BoardState(
    val boards: List<BoardContentResponse> = emptyList(),
    val isRefresh: Boolean = false
)
sealed interface BoardSideEffect {
    data object BoardLoading : BoardSideEffect
    data object BoardSuccess : BoardSideEffect
    data object BoardFailed : BoardSideEffect
}

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BoardState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<BoardSideEffect>()
    val uiEffect = _uiEffect.asSharedFlow()



    fun getBoardContents(){
        Log.d("굿", "호출됐음");
        val range = (200L..600L)
        viewModelScope.launch(Dispatchers.Main) {
            try{
                _uiEffect.emit(BoardSideEffect.BoardLoading)
                delay(range.random())
                val response = RetrofitClient.boardService.getBoardContents()
                _uiState.update {it.copy(boards = response)}
                _uiEffect.emit(BoardSideEffect.BoardSuccess)
                Log.d("TAG", "!${uiState.value.boards}");
            } catch (e:Exception){
                _uiEffect.emit(BoardSideEffect.BoardFailed)
            }
        }
    }
    fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        _uiEffect.emit(BoardSideEffect.BoardLoading)
        getBoardContents()
        _uiState.update { it.copy(isRefresh = false) }
    }

    fun likeBoard(id:Long) {
        viewModelScope.launch(Dispatchers.Main) {
            try{
                RetrofitClient.myLikeUpService.upLikes(id.toString())
            }catch (e:Exception){
                Log.d("TAG", "좋아요 실패");
            }
        }
    }
    fun unLikeBoard(id:Long) {
        viewModelScope.launch(Dispatchers.Main) {
            try{
                RetrofitClient.myLikeDownService.downLikes(id.toString())
            }catch (e:Exception){
                Log.d("TAG", "좋아요 실패");
            }
        }
    }
}
