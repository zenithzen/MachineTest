package com.app.machinetest.models

import kotlinx.serialization.Serializable

@Serializable
data class FeelsLike(
    val day: Double?,
    val eve: Double?,
    val morn: Double?,
    val night: Double?
)