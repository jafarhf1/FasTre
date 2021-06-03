package com.example.fastre.core.utils

import com.example.fastre.core.data.source.local.entity.NewsEntity
import com.example.fastre.core.data.source.remote.response.news.NewsResponse
import com.example.fastre.core.domain.model.News

object NewsDataMapper {
    fun mapResponsesToEntities(input: List<NewsResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                id = it.id,
                title = it.title,
                detail = it.detail,
                date = it.date,
                photo = it.photo,
                isBookmarked = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                id = it.id,
                title = it.title,
                detail = it.detail,
                date = it.date,
                photo = it.photo,
                isBookmarked = it.isBookmarked
            )
        }

    fun mapDomainToEntity(input: News) = NewsEntity(
        id = input.id,
        title = input.title,
        detail = input.detail,
        date = input.date,
        photo = input.photo,
        isBookmarked = input.isBookmarked
    )
}