package com.uc.vpfinalproject.retrovit

import com.uc.vpfinalproject.helper.Const
import com.uc.vpfinalproject.model.*
import com.uc.vpfinalproject.model.NoteRequest.createNoteRequest
import com.uc.vpfinalproject.model.NoteRequest.createNoteResponse
import com.uc.vpfinalproject.model.NoteRequest.deleteNoteResponse
import com.uc.vpfinalproject.model.auth.*
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    //ping server (for delay splashscreen)
    @GET(Const.PING_URL)
    suspend fun pingServer(): Response<DataPingResponse>

    //login
    @Headers("Content-Type: application/json")
    @POST(Const.LOGIN_URL)
    suspend fun login(@Body loginRequest: DataLoginRequest): Response<DataLoginResponse>

    //register
    @Headers("Content-Type: application/json")
    @POST(Const.REGISTER_URL)
    suspend fun register(@Body registerRequest: DataRegisterRequest): Response<DataRegisterResponse>

    //logout
    @DELETE(Const.LOGOUT_URL)
    suspend fun logoutUser(@Header("Authorization") token: String): Response<DataLogoutResponse>

    //get-user
    @GET(Const.USER_URL)
    suspend fun getUser(@Header("Authorization") token: String): Response<DataUserResponse>

    //get-note

    //create-note
    @Headers("Content-Type: application/json")
    @POST(Const.REGISTER_URL)
    suspend fun createNote(@Body createRequest: createNoteRequest): Response<createNoteResponse>

    //delete-note
    @DELETE(Const.LOGOUT_URL)
    suspend fun deleteNote(@Body deleteRequest: deleteNoteResponse): Response<deleteNoteResponse>



    companion object {
        fun getApi(): RestApi? {
            return ServiceBuilder.buildService?.create(RestApi::class.java)
        }
    }
}