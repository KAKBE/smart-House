package com.example.myapplication.web

import com.example.myapplication.data.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("get/Light/Lux")
    suspend fun getLightLux(): DataLight

    @POST("set/Light/Lux")
    suspend fun setLightLux(@Body state: DataLight)

    @GET("get/Light/Sleep")
    suspend fun getLightSleep(): DataLightSleep

    @POST("set/Light/Sleep")
    suspend fun setLightSleep(state: DataLightSleep)

    @GET("get/Access/Photo/")
    suspend fun getAccessPhoto(): DataDoorPhoto

    @GET("get/Door/History")
    suspend fun getDoorHistory(): DataDoorHistory

    @GET("get/Climate/ClimateStation")
    suspend fun getClimateClimateStation(): DataClimateStation

    @POST("set/Climate/ClimateStation")
    suspend fun setClimateClimateStation(state: DataClimateStation)

    @GET("get/Climate/History")
    suspend fun getClimateHistory(): DataClimateStation

    @GET("get/Climate/Comfort")
    suspend fun getClimateComfort(): DataClimateStation

    @POST("set/Climate/Comfort")
    suspend fun setClimateComfort(state: DataClimateStation)

    @GET("get/Climate/Online")
    suspend fun getClimateOnline(): DataClimateStation

    @GET("get/ElectricityConsumption/history")
    suspend fun getElectricityConsumptionHistory(): DataElectroHistory



}

