package com.app.machinetest.models

import kotlinx.serialization.Serializable

@Serializable
data class Minutely(
    val dt: Int?,
    val precipitation: Int?
)