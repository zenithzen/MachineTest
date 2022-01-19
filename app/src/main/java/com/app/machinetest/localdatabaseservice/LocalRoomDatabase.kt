package com.app.machinetest.localdatabaseservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.machinetest.localdatabaseservice.entities.UserEntity

/** Created by Jishnu P Dileep on 27-05-2021 */

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class LocalRoomDatabase : RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract fun appLocalRoomDatabaseDao(): AppLocalRoomDatabaseDao
}