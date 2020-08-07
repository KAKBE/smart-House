package com.example.myapplication.data

class DataClimateStation (
    val pechka : Boolean,
    val okno : Boolean,
    val yvlaznitel : Boolean,
    val time_pechka : String,
    val time_okno : String,
    val time_yvlaznitel : String

)

class DataClimateComfort (
    val state: Boolean,
    val min_comfort_temperature: Int,
    val max_comfort_temperature: Int,
    val min_comfort_vlaznost: Int,
    val max_comfort_vlaznost: Int

)

class DataClimateHistory(
    val history: Array<ClimateHistoryItem>
)

class ClimateHistoryItem (
    val davlenie: Int,
    val Vlaznost: Int,
    val temperature: Int,
    val temperature_home: Int,
    val time: String
)



class DataClimateOnline(
    val history: Array<ClimateOnlineItem>
)

class ClimateOnlineItem (
    val temperatura_za_oknom: Int,
    val temperatura_v_komnate: Int,
    val davlenie: Int,
    val vlaznost: Int
)


