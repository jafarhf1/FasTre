package com.example.fastre.core.domain.usecase

import com.example.fastre.core.domain.model.News
import com.example.fastre.core.domain.repository.IRepository

class MyInteractor (private val repository: IRepository): MyUseCase {
    override fun getAllNews() = repository.getAllNews()
    override fun getFavoriteNews() = repository.getFavoriteNews()
    override fun setFavoriteNews(news: News, state: Boolean) = repository.setFavoriteNews(news, state)

    override fun getAllHospital() = repository.getAllHospital()
    override fun getAllSchedule() = repository.getAllSchedule()
    override fun getAllPoly() = repository.getAllPoly()
    override fun getAllMedicalRecords() = repository.getAllMedicalRecords()
}