package com.project.estudo.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.estudo.dataBase.dao.OldResultDao
import com.project.estudo.domain.model.OldResultTable

@Database(entities = [OldResultTable::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract val oldResultDao: OldResultDao

    companion object {

        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "old_result_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}