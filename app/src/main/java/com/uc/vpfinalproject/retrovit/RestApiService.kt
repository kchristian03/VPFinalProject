package com.uc.vpfinalproject.retrovit

import com.uc.vpfinalproject.helper.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class RestApiService {
    private lateinit var restApi: RestApi

    fun getRestApi(): RestApi {
        if (!::restApi.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            restApi = retrofit.create(RestApi::class.java)
        }
        return restApi
    }

}
