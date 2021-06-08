package com.example.fastre.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.fastre.core.data.source.local.LocalDataSource
import com.example.fastre.core.data.source.remote.RemoteDataSource
import com.example.fastre.core.data.source.remote.network.ApiResponse
import com.example.fastre.core.data.source.remote.response.hospital.HospitalResponse
import com.example.fastre.core.data.source.remote.response.medicalRecords.MedicalRecordsResponse
import com.example.fastre.core.data.source.remote.response.news.NewsResponse
import com.example.fastre.core.data.source.remote.response.polyclinic.PolyclinicResponse
import com.example.fastre.core.data.source.remote.response.schedule.ScheduleResponse
import com.example.fastre.core.domain.model.*
import com.example.fastre.core.domain.repository.IRepository
import com.example.fastre.core.utils.*

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllNews(): LiveData<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<News>> {
                return Transformations.map(localDataSource.getAllNews()) {
                    NewsDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getAllNews()

            override fun saveCallResult(data: List<NewsResponse>) {
                val newsList = NewsDataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asLiveData()

    override fun getFavoriteNews(): LiveData<List<News>> {
        return Transformations.map(localDataSource.getFavoriteNews()) {
            NewsDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = NewsDataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(newsEntity, state) }
    }

    override fun getAllHospital(): LiveData<Resource<List<Hospital>>> =
        object : NetworkBoundResource<List<Hospital>, List<HospitalResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Hospital>> {
                return Transformations.map(localDataSource.getAllHospital()) {
                    HospitalDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Hospital>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<HospitalResponse>>> =
                remoteDataSource.getAllHospital()

            override fun saveCallResult(data: List<HospitalResponse>) {
                val list = HospitalDataMapper.mapResponsesToEntities(data)
                localDataSource.insertHospital(list)
            }
        }.asLiveData()

    override fun getAllMedicalRecords(): LiveData<Resource<List<MedicalRecords>>> =
            object : NetworkBoundResource<List<MedicalRecords>, List<MedicalRecordsResponse>>(appExecutors) {
                override fun loadFromDB(): LiveData<List<MedicalRecords>> {
                    return Transformations.map(localDataSource.getAllMedicalRecords()) {
                        MedicalRecordsDataMapper.mapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<MedicalRecords>?): Boolean = true

                override fun createCall(): LiveData<ApiResponse<List<MedicalRecordsResponse>>> =
                        remoteDataSource.getAllMedicalRecords()

                override fun saveCallResult(data: List<MedicalRecordsResponse>) {
                    val medicalRecordsList = MedicalRecordsDataMapper.mapResponsesToEntities(data)
                    localDataSource.insertMedicalRecords(medicalRecordsList)
                }
            }.asLiveData()

    override fun getAllSchedule(): LiveData<Resource<List<Schedule>>> =
        object : NetworkBoundResource<List<Schedule>, List<ScheduleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Schedule>> {
                return Transformations.map(localDataSource.getAllSchedule()) {
                    ScheduleDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Schedule>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ScheduleResponse>>> =
                remoteDataSource.getAllSchedule()

            override fun saveCallResult(data: List<ScheduleResponse>) {
                val list = ScheduleDataMapper.mapResponsesToEntities(data)
                localDataSource.insertSchedule(list)
            }
        }.asLiveData()


    override fun getAllPoly(): LiveData<Resource<List<Poly>>> =
        object : NetworkBoundResource<List<Poly>, List<PolyclinicResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Poly>> {
                return Transformations.map(localDataSource.getAllPoly()) {
                    PolyDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Poly>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<PolyclinicResponse>>> =
                remoteDataSource.getAllPoly()

            override fun saveCallResult(data: List<PolyclinicResponse>) {
                val list = PolyDataMapper.mapResponsesToEntities(data)
                localDataSource.insertPoly(list)
            }
        }.asLiveData()
}