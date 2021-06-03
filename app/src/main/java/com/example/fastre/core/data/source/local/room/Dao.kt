package com.example.fastre.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.fastre.core.data.source.local.entity.*

@Dao
interface Dao {
    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsEntity>>
    @Query("SELECT * FROM news where isBookmarked = 1")
    fun getFavoriteNews(): LiveData<List<NewsEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsEntity>)
    @Update
    fun updateFavoriteNews(news: NewsEntity)

    @Query("SELECT * FROM hospital")
    fun getAllHospital(): LiveData<List<HospitalEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHospital(hospital: List<HospitalEntity>)

    @Query("SELECT * FROM schedule")
    fun getAllSchedule(): LiveData<List<ScheduleEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(schedule: List<ScheduleEntity>)

    @Query("SELECT * FROM medical_records")
    fun getAllMedicalRecords(): LiveData<List<MedicalRecordsEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicalRecord(hospital: List<MedicalRecordsEntity>)

    @Query("SELECT * FROM poly")
    fun getAllPoly(): LiveData<List<PolyEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPoly(schedule: List<PolyEntity>)
}