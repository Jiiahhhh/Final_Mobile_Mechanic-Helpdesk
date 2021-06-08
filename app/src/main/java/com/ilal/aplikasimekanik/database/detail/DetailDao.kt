package com.ilal.aplikasimekanik.database.detail

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DetailDao {
    @Query("SELECT * FROM detail")
    fun getDetail(): LiveData<List<Detail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetail(detail: Detail)

    @Delete
    suspend fun deleteDetail(detail: Detail)

    @Update
    suspend fun updateDetail(detail: Detail)
}