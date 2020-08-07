package com.example.myapplication.web

import com.example.myapplication.data.DataDoor
import com.example.myapplication.data.DataLight
import com.example.myapplication.data.DataClimateStation
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("get/Light/Lux")
    suspend fun getLightLux(): DataLight

    @POST("set/Light/Lux")
    suspend fun setLightLux(state: DataLight)

    @GET("get/Door/Open")
    suspend fun getDoorOpen(): DataDoor

    @POST("set/Door/Open")
    suspend fun setDoorOpen(state: DataDoor)

    @GET("get/Climate/Station")
    suspend fun getClimateStation(): DataClimateStation

    @POST("set/Climate/Station")
    suspend fun getClimateStation(state: DataClimateStation)

}

