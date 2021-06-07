package com.example.fastre.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.fastre.core.data.source.Resource
import com.example.fastre.core.domain.model.*

interface MyUseCase {
    fun getAllNews(): LiveData<Resource<List<News>>>
    fun getFavoriteNews(): LiveData<List<News>>
    fun setFavoriteNews(news: News, state: Boolean)

    fun getAllHospital(): LiveData<Resource<List<Hospital>>>
    fun getAllSchedule(): LiveData<Resource<List<Schedule>>>
    fun getAllPoly(): LiveData<Resource<List<Poly>>>
    fun getAllMedicalRecords(): LiveData<Resource<List<MedicalRecords>>>
}