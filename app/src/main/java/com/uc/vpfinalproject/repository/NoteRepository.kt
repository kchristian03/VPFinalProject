package com.uc.vpfinalproject.repository

import com.uc.vpfinalproject.model.note.*
import com.uc.vpfinalproject.retrovit.RestApi
import retrofit2.Response

class NoteRepository {

    suspend fun createNote(token: String, createNoteRequest: CreateNoteRequest): Response<CreateNoteResponse>?{
        return RestApi.getApi()?.createNote(token = token, createNoteRequest = createNoteRequest)
    }

    suspend fun getNote(token: String): Response<GetNoteResponse>?{
        return RestApi.getApi()?.getNote(token = token)
    }

    suspend fun editNote(token: String, editNoteRequest: EditNoteRequest): Response<EditNoteResponse>?{
        return RestApi.getApi()?.editNote(token = token, editNoteRequest = editNoteRequest)
    }

    suspend fun deleteNote(token: String): Response<DeleteNoteResponse>?{
        return RestApi.getApi()?.deleteNote(token = token)
    }
}
