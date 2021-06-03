package com.example.fastre.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastre.core.di.Injection
import com.example.fastre.core.domain.usecase.MyUseCase
import com.example.fastre.ui.hospital.HospitalViewModel
import com.example.fastre.ui.news.BookmarkedNewsViewModel
import com.example.fastre.ui.news.NewsDetailViewModel
import com.example.fastre.ui.news.NewsViewModel

class ViewModelFactory private constructor(private val useCase: MyUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                instance
                    ?: ViewModelFactory(
                        Injection.provideNewsUseCase(context)
                    )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(useCase) as T
            }
            modelClass.isAssignableFrom(NewsDetailViewModel::class.java) -> {
                NewsDetailViewModel(useCase) as T
            }
            modelClass.isAssignableFrom(BookmarkedNewsViewModel::class.java) -> {
                BookmarkedNewsViewModel(useCase) as T
            }
            modelClass.isAssignableFrom(HospitalViewModel::class.java) -> {
                HospitalViewModel(useCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}