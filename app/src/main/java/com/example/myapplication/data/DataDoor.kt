package com.example.myapplication.data

class DataDoor (
    val state : Boolean
)

class DataDoorPhoto (
    val photo : String
)

class DataDoorHistory(
    val history: Array<DoorHistoryItem>
)

class DoorHistoryItem (
    val date: String,
    val time: String,
    val mecto: String
)

