package com.example.test.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AQMResponseItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val aqi: Double,
    val city: String
)