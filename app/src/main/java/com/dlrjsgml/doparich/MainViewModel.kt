package com.dlrjsgml.doparich

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlrjsgml.doparich.data.info.UserInfo
import com.dlrjsgml.doparich.data.info.UserRepository
import com.dlrjsgml.doparich.data.info.UserRepository.Companion.NAME
import com.dlrjsgml.doparich.data.info.UserRepository.Companion.USERID
import com.dlrjsgml.doparich.data.info.UserRepository.Companion.USERPW
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    val userName: MutableLiveData<String> = MutableLiveData("")
    val userId: MutableLiveData<String> = MutableLiveData("")
    val userPw: MutableLiveData<String> = MutableLiveData("")

    var userInfos: MutableLiveData<UserInfo> = MutableLiveData()

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.saveUserInfo(
                UserInfo(
                    userName = userName.value!!,
                    userId = userId.value!!,
                    userPw = userPw.value!!
                )
            )
        }
    }

    fun retrieveDate() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUserInfo().collect{
                userInfos.postValue(it)
            }
        }
    }

}