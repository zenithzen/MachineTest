package com.app.machinetest.models

import kotlinx.serialization.Serializable

@Serializable
data class Climate(
    val current: Current?,
  /*  val daily: List<Daily>?,
    val hourly: List<Hourly>?,
    val lat: Double?,
    val lon: Double?,
    val minutely: List<Minutely>?,
    val timezone: String?,
    val timezone_offset: Int?*/
)