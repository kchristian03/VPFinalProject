package com.uc.vpfinalproject.viewmodel.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.vpfinalproject.model.BaseResponse
import com.uc.vpfinalproject.model.note.*
import com.uc.vpfinalproject.repository.NoteRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepo = NoteRepository()
    val createNoteResult: MutableLiveData<BaseResponse<CreateNoteResponse>> = MutableLiveData()
    val getNoteResult : MutableLiveData<BaseResponse<GetNoteResponse>> = MutableLiveData()
    val editNoteResult : MutableLiveData<BaseResponse<EditNoteResponse>> = MutableLiveData()
    val deleteNoteResult: MutableLiveData<BaseResponse<DeleteNoteResponse>> = MutableLiveData()

    fun createNote(token: String, title: String, content: String, user_id: Int){
        createNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val token = "Bearer $token"
                val createNoteRequest = CreateNoteRequest(
                    title = title,
                    content = content,
                    user_id = user_id,
                )
                val response = noteRepo.createNote(token, createNoteRequest)
                if(response?.code() == 200){
                    createNoteResult.value = BaseResponse.Success(response.body())
                }else if (response?.code() == 400) {
                    createNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    createNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception){
                createNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun getNote (token: String) {
        getNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val token = "Bearer $token"
                val response = noteRepo.getNote(token)
                if (response?.code() == 200) {
                    getNoteResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    getNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    getNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                getNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun editNote (id: Int, token: String, title: String, content: String){
        editNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val token = "Bearer $token"
                val editNoteRequest = EditNoteRequest(
                    title = title,
                    content = content
                )
                val response = noteRepo.editNote(id, token, editNoteRequest)
                if (response?.code() == 200) {
                    editNoteResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    editNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    editNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                editNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun deleteNote (id: Int, token: String) {
        deleteNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val token = "Bearer $token"
                val response = noteRepo.deleteNote(id, token)
                if (response?.code() == 200) {
                    deleteNoteResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    deleteNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    deleteNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                deleteNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}