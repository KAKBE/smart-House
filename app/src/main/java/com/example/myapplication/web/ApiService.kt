package com.example.myapplication.web

import com.example.myapplication.data.DataDoor
import com.example.myapplication.data.DataLight
import com.example.myapplication.data.DataClimateStation
import com.example.myapplication.data.DataElectrOHistory
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("get/Light/Lux")
    suspend fun getLightLux(): DataLight

    @POST("set/Light/Lux")
    suspend fun setLightLux(state: DataLight)

    @GET("get/Light/Sleep")
    suspend fun getLightSleep(): DataLight

    @POST("set/Light/Sleep")
    suspend fun setLightSleep(state: DataLight)

    @GET("get/Access/Photo")
    suspend fun getAccessPhoto(): DataDoor

    @GET("get/Door/History")
    suspend fun getDoorHistory(): DataDoor

    @GET("get/Climate/ClimateStation")
    suspend fun getClimateClimateStation(): DataClimateStation

    @GET("get/Climate/History")
    suspend fun getClimateHistory(): DataClimateStation

    @GET("get/Climate/Comfort")
    suspend fun getClimateComfort(): DataClimateStation

    @GET("get/Climate/Online")
    suspend fun getClimateOnline(): DataClimateStation

    @GET("get/ElectricityConsumption/history")
    suspend fun getElectricityConsumptionHistory(): DataElectrOHistory



}

