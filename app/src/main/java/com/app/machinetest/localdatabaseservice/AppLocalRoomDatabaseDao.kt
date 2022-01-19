package com.app.machinetest.localdatabaseservice

import androidx.room.*
import com.app.machinetest.localdatabaseservice.entities.UserEntity

/** Created by Jishnu P Dileep on 27-05-2021 */

@Dao
interface AppLocalRoomDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long

    @Query("select * From user ORDER BY userId ASC")
    suspend fun fetch(): List<UserEntity>

    @Query("DELETE FROM user")
    suspend fun clearDb()

    @Delete
    suspend fun delete(user: UserEntity):Int

  /*  @Query("DELETE FROM user WHERE userId = :id")
    suspend fun deleteById(id: Long):Int*/
}