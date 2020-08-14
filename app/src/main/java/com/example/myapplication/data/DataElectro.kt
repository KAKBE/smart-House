package com.example.myapplication.data

class DataElectroHistory(
    val climate: List<ElectroHistoryItem>
)

class ElectroHistoryItem (
    val every_day_consumption: Int,
    val time_day: String,
    val every_month_consumption: Int,
    val time_month: String,
    val every_week_consumption: Int,
    val time_week: String
)