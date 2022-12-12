package com.uc.vpfinalproject.viewmodel.Yoga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YogaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is yoga Fragment"
    }
    val text: LiveData<String> = _text
}