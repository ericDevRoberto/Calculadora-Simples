package com.project.estudo.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.estudo.domain.model.OldResultTable

@Dao
interface OldResultDao {

    @Insert
    suspend fun insert(oldResultTable: OldResultTable)

    @Query("SELECT * FROM old_result_table ORDER BY oldResultId DESC LIMIT 1")
    suspend fun getLastResult() : OldResultTable

    @Query("SELECT * FROM old_result_table ORDER BY oldResultId DESC LIMIT 1, 1")
    suspend fun getNextLastResult() : OldResultTable

}