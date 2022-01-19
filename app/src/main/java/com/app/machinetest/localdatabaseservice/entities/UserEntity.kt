package com.app.machinetest.localdatabaseservice.entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,
    val fName: String,
    val lName: String,
    val email: String,

    )