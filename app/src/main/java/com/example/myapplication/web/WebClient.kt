package com.example.myapplication.web

import com.example.myapplication.data.DataLight
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()


    val api = Retrofit.Builder()
        .baseUrl("https://google.com") // Адрес API, нужно узнать у команды
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)

    suspend fun getLightState(): DataLight {
        return withContext(Dispatchers.IO) {
            api.getLightLux()
        }
    }

    suspend fun setLightState(state: DataLight) {
        return withContext(Dispatchers.IO) {
            api.setLightLux(state)
        }
    }


}
