package com.example.fastre.ui.news

import androidx.lifecycle.ViewModel
import com.example.fastre.core.domain.usecase.MyUseCase

class NewsViewModel (useCase: MyUseCase) : ViewModel() {
    val news = useCase.getAllNews()
}