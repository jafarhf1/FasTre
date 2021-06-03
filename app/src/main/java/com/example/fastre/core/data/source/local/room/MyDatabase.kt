package com.example.fastre.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fastre.core.data.source.local.entity.*

@Database(entities = [
    NewsEntity::class,
    HospitalEntity::class,
    ScheduleEntity::class,
    MedicalRecordsEntity::class,
    PolyEntity::class
], version = 1, exportSchema = false)

abstract class MyDatabase: RoomDatabase() {

    abstract fun myDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "fastreDatabase.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }

}
