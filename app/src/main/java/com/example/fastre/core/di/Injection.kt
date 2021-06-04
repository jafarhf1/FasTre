package com.example.fastre.core.di

import android.content.Context
import com.example.fastre.core.data.source.Repository
import com.example.fastre.core.data.source.local.LocalDataSource
import com.example.fastre.core.data.source.local.room.MyDatabase
import com.example.fastre.core.data.source.remote.RemoteDataSource
import com.example.fastre.core.data.source.remote.network.ApiConfig
import com.example.fastre.core.domain.repository.IRepository
import com.example.fastre.core.domain.usecase.MyInteractor
import com.example.fastre.core.domain.usecase.MyUseCase
import com.example.fastre.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IRepository {
        val database = MyDatabase.getInstance(context)

<<<<<<< HEAD
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.instance)
=======
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
        val localDataSource = LocalDataSource.getInstance(database.myDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideNewsUseCase(context: Context): MyUseCase {
        val repository = provideRepository(context)
        return MyInteractor(repository)
    }
}