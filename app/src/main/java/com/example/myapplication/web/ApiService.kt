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

    @POST("set/door/open")
    suspend fun setDoorOpen()

    @GET("get/Light/Sleep")
    suspend fun getLightSleep(): DataLightSleep

    @POST("set/Light/Sleep")
    suspend fun setLightSleep(@Body state: DataLightSleep)

    @GET("get/Access/Photo/")
    suspend fun getAccessPhoto(): DataDoorPhoto

    @GET("get/door/history")
    suspend fun getDoorHistory(): List<DoorHistoryItem>

    @GET("get/Climate/ClimatStation")
    suspend fun getClimateClimateStation(): DataClimateStation

    @POST("set/Climate/ClimatStation")
    suspend fun setClimateClimateStation(@Body state: DataClimateStation)

    @GET("get/Climate/history")
    suspend fun getClimateHistory(): List<ClimateHistoryItem>

    @GET("get/Climate/Comfort")
    suspend fun getClimateComfort(): DataClimateStation

    @POST("set/Climate/comfort")
    suspend fun setClimateComfort(@Body state: DataClimateComfort)

    @GET("get/Climate/Online")
    suspend fun getClimateOnline(): DataClimateOnline

    @GET("get/ElectricityConsumption/history")
    suspend fun getElectricityConsumptionHistory(): DataElectroHistory

    @POST("set/token")
    suspend fun setToken(@Body token: TokenRequest)





}

