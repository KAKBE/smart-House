package com.example.myapplication.web

import com.example.myapplication.data.*
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
    var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val okhttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    val api = Retrofit.Builder()
        .baseUrl("https://ms.newtonbox.ru/smarthome4/").client(okhttp) // Адрес API, нужно узнать у команды
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)

    suspend fun getLightLux(): DataLight {
        return withContext(Dispatchers.IO) {
            api.getLightLux()
        }
    }

    suspend fun setLightLux(state: DataLight) {
        return withContext(Dispatchers.IO) {
            api.setLightLux(state)
        }
    }

    suspend fun setDoorOpen() {
        return withContext(Dispatchers.IO) {
            api.setDoorOpen()
        }
    }

    suspend fun getLightSleep(): DataLightSleep {
        return withContext(Dispatchers.IO) {
            api.getLightSleep()
        }
    }

    suspend fun setLightSleep(state: DataLightSleep) {
        return withContext(Dispatchers.IO) {
            api.setLightSleep(state)
        }
    }

    suspend fun getAccessPhoto(): DataDoorPhoto {
        return withContext(Dispatchers.IO) {
            api.getAccessPhoto()
        }
    }

    suspend fun getDoorHistory(): List<DoorHistoryItem> {
        return withContext(Dispatchers.IO) {
            api.getDoorHistory()
        }
    }

    suspend fun getClimateStation(): DataClimateStation {
        return withContext(Dispatchers.IO) {
            api.getClimateClimateStation()
        }
    }

    suspend fun setClimateClimateStation(data:DataClimateStation) {
        return withContext(Dispatchers.IO) {
            api.setClimateClimateStation(data)
        }
    }

    suspend fun getClimateHistory(): List<ClimateHistoryItem> {
        return withContext(Dispatchers.IO) {
            api.getClimateHistory()
        }
    }

    suspend fun getClimateComfort(): DataClimateStation {
        return withContext(Dispatchers.IO) {
            api.getClimateComfort()
        }
    }

    suspend fun setClimateComfort(data:DataClimateComfort) {
        return withContext(Dispatchers.IO) {
            api.setClimateComfort(data)
        }
    }

    suspend fun getClimateOnline(): DataClimateOnline {
        return withContext(Dispatchers.IO) {
            api.getClimateOnline()
        }
    }

    suspend fun getElectricityConsumptionHistory(): DataElectroHistory {
        return withContext(Dispatchers.IO) {
            api.getElectricityConsumptionHistory()
        }
    }

    suspend fun setToken(token:TokenRequest) {
        return withContext(Dispatchers.IO) {
            api.setToken(token)
        }
    }




}
