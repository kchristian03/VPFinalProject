package com.uc.vpfinalproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.vpfinalproject.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: UserRepository):ViewModel() {

    val _name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val _token: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val _user_id: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val _id: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val _expires_at: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val name: LiveData<String>
        get() = _name
    val token: LiveData<String>
        get() = _token
    val user_id: LiveData<Int>
        get() = _user_id
    val id: LiveData<Int>
        get() = _id
    val expires_at: LiveData<String>
        get() = _expires_at

    fun loginVM() = viewModelScope.launch {
        repository.login().let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    _name.postValue(it.data.name)
                    _token.postValue(it.data.token)
                    _user_id.postValue(it.data.user_id)
                    _id.postValue(it.data.id)
                    _expires_at.postValue(it.data.expires_at)
                }
            } else {
                Log.e("Retrieve Data", "Failed!")
            }
        }
    }
}