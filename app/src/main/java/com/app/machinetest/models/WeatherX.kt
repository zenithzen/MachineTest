package com.app.machinetest.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherX(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)