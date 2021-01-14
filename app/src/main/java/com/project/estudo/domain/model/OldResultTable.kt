package com.project.estudo.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "old_result_table")
class OldResultTable {

    @PrimaryKey(autoGenerate = true)
    var oldResultId: Long = 0L

    @ColumnInfo(name = "oldResultValue")
    var oldResultValue = String()
}