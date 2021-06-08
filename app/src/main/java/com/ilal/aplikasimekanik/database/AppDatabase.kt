package com.ilal.aplikasimekanik.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilal.aplikasimekanik.database.detail.Detail
import com.ilal.aplikasimekanik.database.detail.DetailDao

@Database(entities = [Detail::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun detailDao(): DetailDao

    companion object{
        private const val DB_NAME = "DETAIL_DB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                        .build()
                }
            }
            return instance
        }
    }
}