package com.uc.vpfinalproject.helper

object Const {
    const val BASE_URL_LOCALHOST = "http://192.168.42.19:8080/"
    const val BASE_URL = "http://8.222.144.75:2197/"
    const val PING_URL = "api/hello/"

    //auth
    const val LOGIN_URL = "auth/login/"
    const val REGISTER_URL = "auth/register/"
    const val USER_URL = "auth/user/"
    const val LOGOUT_URL = "auth/user/logout/"

    //note
    const val CREATE_NOTE_URL = "api/notes/create"
    const val GET_NOTE_URL = "api/notes/get"
    const val EDIT_NOTE_URL = "api/notes/edit/{id}"
    const val DELETE_NOTE_URL = "api/notes/delete/{id}"


}