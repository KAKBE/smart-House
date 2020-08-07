package com.example.myapplication.web

import com.example.myapplication.data.DataClimateStation
import com.example.myapplication.data.DataDoor
import com.example.myapplication.data.DataElectrOHistory
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

    suspend fun getLightSleep(): DataLight {
        return withContext(Dispatchers.IO) {
            api.getLightLux()
        }
    }

    suspend fun setLightSleep(state: DataLight) {
        return withContext(Dispatchers.IO) {
            api.setLightSleep(state)
        }
    }

    suspend fun getAccessPhoto(): DataDoor {
        return withContext(Dispatchers.IO) {
            api.getAccessPhoto()
        }
    }

    suspend fun getDoorHistory(): DataDoor {
        return withContext(Dispatchers.IO) {
            api.getDoorHistory()
        }
    }

    suspend fun getClimateClimateStation(): DataClimateStation {
        return withContext(Dispatchers.IO) {
            api.getClimateClimateStation()
        }
    }

    suspend fun getClimateHistory(): DataClimateStation {
        return withContext(Dispatchers.IO) {
            api.getClimateHistory()
        }
    }

    suspend fun getClimateComfort(): DataClimateStation {
        return withContext(Dispatchers.IO) {
            api.getClimateComfort()
        }
    }

    suspend fun getClimateOnline(): DataClimateStation {
        return withContext(Dispatchers.IO) {
            api.getClimateOnline()
        }
    }

    suspend fun getElectricityConsumptionHistory(): DataElectrOHistory {
        return withContext(Dispatchers.IO) {
            api.getElectricityConsumptionHistory()
        }
    }


}
