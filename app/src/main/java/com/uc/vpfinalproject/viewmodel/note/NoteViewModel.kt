package com.uc.vpfinalproject.viewmodel.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.NoteRequest.*
import com.uc.vpfinalproject.model.auth.*
import com.uc.vpfinalproject.repository.NoteRepository
import com.uc.vpfinalproject.repository.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class NoteViewModel : ViewModel() {

    private val noteRepo = NoteRepository()
    val createNoteResponse: MutableLiveData<BaseResponse<createNoteResponse>> = MutableLiveData()
    val getNoteResponse : MutableLiveData<BaseResponse<getNoteResponse>> = MutableLiveData()
    val deleteNoteResponse: MutableLiveData<BaseResponse<deleteNoteResponse>> = MutableLiveData()
    val editNoteResponse : MutableLiveData<BaseResponse<editNoteResponse>> = MutableLiveData()

    fun createNote(title: String, content: String, user_id: Int){
        createNoteResponse.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val createNoteRequest = createNoteRequest(
                    title = title,
                    content = content,
                    user_id = user_id,
                    token = "token"
                )
                val response = noteRepo.createNote(createNoteRequest)
                if(response?.code() == 200){
                    createNoteResponse.value = BaseResponse.Success(response.body())
                }else if (response?.code() == 400) {
                    createNoteResponse.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    createNoteResponse.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception){
                createNoteResponse.value = BaseResponse.Error(ex.message)
            }
        }
    }


}