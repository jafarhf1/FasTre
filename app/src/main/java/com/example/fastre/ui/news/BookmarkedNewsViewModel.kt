package com.example.fastre.ui.news

import androidx.lifecycle.ViewModel
import com.example.fastre.core.domain.usecase.MyUseCase

class BookmarkedNewsViewModel (useCase: MyUseCase) : ViewModel() {
    val favoriteNews = useCase.getFavoriteNews()
}