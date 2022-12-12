package com.uc.vpfinalproject.viewmodel.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is note Fragment"
    }
    val text: LiveData<String> = _text
}