package com.uc.vpfinalproject.repository

import com.uc.vpfinalproject.model.NoteRequest.createNoteRequest
import com.uc.vpfinalproject.model.NoteRequest.createNoteResponse
import com.uc.vpfinalproject.model.auth.*
import com.uc.vpfinalproject.retrovit.RestApi
import retrofit2.Response

class NoteRepository {

    suspend fun createNote(createNoteRequest: createNoteRequest): Response<createNoteResponse>?{
        return RestApi.getApi()?.createNote(createRequest = createNoteRequest)
    }



}