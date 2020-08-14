package com.example.myapplication.data

class DataDoor (
    val state : Boolean
)

class DataDoorPhoto (
    val photo : String
)

class DataDoorHistory(
    val history: List<DoorHistoryItem>
)

class DoorHistoryItem (
    val date: String,
    val time: String,
    val mecto: String
)

