package com.example.myapplication.data

class DataClimateStation (
    val state_p : Boolean,
    val state_o : Boolean,
    val state_y : Boolean,
    val time_p : String,
    val time_o : String,
    val time_y : String

)

class DataClimateComfort (
    val state: Boolean,
    val min_t: Int,
    val max_t: Int,
    val min_v: Int,
    val max_v: Int

)

class DataClimateHistory(
    val history: Array<ClimateHistoryItem>
)

class ClimateHistoryItem (

)



class DataClimateOnli(
    val history: Array<ClimateOnlyItem>
)

class ClimateOnlyItem {

}


