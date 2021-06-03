package com.example.fastre.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.fastre.core.data.source.Resource
import com.example.fastre.core.domain.model.Hospital
import com.example.fastre.core.domain.model.News
import com.example.fastre.core.domain.model.Poly
import com.example.fastre.core.domain.model.Schedule

interface MyUseCase {
    fun getAllNews(): LiveData<Resource<List<News>>>
    fun getFavoriteNews(): LiveData<List<News>>
    fun setFavoriteNews(news: News, state: Boolean)

    fun getAllHospital(): LiveData<Resource<List<Hospital>>>
    fun getAllSchedule(): LiveData<Resource<List<Schedule>>>
    fun getAllPoly(): LiveData<Resource<List<Poly>>>
}