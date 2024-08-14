package com.dlrjsgml.doparich.feature.main.board.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.feature.main.my.MyInfoState
import com.dlrjsgml.doparich.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class BoardResponse(
    val id: Long = 999999999999999,
    val content: String = "오류!",
    val writer: String = "오류!",
    val likes: Long = 999,
)

class DetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BoardResponse())
    val uiState = _uiState.asStateFlow()


    fun getBoard(id: Long) {
        viewModelScope.launch {
            try {
                val boardOneResponse =
                    RetrofitClient.boardOneService.getBoardOneContents(id.toString())
                _uiState.update {
                    it.copy(
                        id = boardOneResponse.id,
                        content = boardOneResponse.content,
                        writer = boardOneResponse.writer,
                        likes = boardOneResponse.likes
                    )
                }

            } catch (e: Exception) {

            }
        }
    }
}