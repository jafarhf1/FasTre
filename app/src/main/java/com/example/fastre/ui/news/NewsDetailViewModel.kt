package com.example.fastre.ui.news

import androidx.lifecycle.ViewModel
import com.example.fastre.core.domain.model.News
import com.example.fastre.core.domain.usecase.MyUseCase

class NewsDetailViewModel (private val useCase: MyUseCase) : ViewModel() {
    fun setFavoriteNews(news: News, newStatus:Boolean) = useCase.setFavoriteNews(news, newStatus)
}