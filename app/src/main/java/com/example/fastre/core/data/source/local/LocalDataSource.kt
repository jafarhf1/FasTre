package com.example.fastre.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.fastre.core.data.source.local.entity.*
import com.example.fastre.core.data.source.local.room.Dao

class LocalDataSource private constructor(private val dao: Dao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(dao)
            }
    }

    fun getAllNews(): LiveData<List<NewsEntity>> = dao.getAllNews()
    fun getFavoriteNews(): LiveData<List<NewsEntity>> = dao.getFavoriteNews()
    fun insertNews(newsList: List<NewsEntity>) = dao.insertNews(newsList)
    fun setFavoriteNews(news: NewsEntity, newState: Boolean) {
        news.isBookmarked = newState
        dao.updateFavoriteNews(news)
    }

    fun getAllHospital(): LiveData<List<HospitalEntity>> = dao.getAllHospital()
    fun insertHospital(hospitalList: List<HospitalEntity>) = dao.insertHospital(hospitalList)

    fun getAllSchedule(): LiveData<List<ScheduleEntity>> = dao.getAllSchedule()
    fun insertSchedule(scheduleList: List<ScheduleEntity>) = dao.insertSchedule(scheduleList)

    //fun getAllMedicalRecords(): LiveData<List<MedicalRecordsEntity>> = dao.getAllMedicalRecords()
    //fun insertMedicalRecords(mrList: List<MedicalRecordsEntity>) = dao.insertMedicalRecord(mrList)

    fun getAllPoly(): LiveData<List<PolyEntity>> = dao.getAllPoly()
    fun insertPoly(polyList: List<PolyEntity>) = dao.insertPoly(polyList)
}