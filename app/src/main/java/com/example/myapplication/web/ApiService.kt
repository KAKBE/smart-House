package com.example.myapplication.web

import com.example.myapplication.data.DataLight
import retrofit2.http.GET

interface ApiService {

    @GET("get/Light/Lux")
    suspend fun getLightLux(): DataLight
}