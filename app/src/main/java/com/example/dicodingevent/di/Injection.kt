package com.example.dicodingevent.di

import android.content.Context
import com.example.dicodingevent.data.controller.EventRepository
import com.example.dicodingevent.data.local.dao.FavoriteDatabase
import com.example.dicodingevent.data.controller.retrofit.ApiConfig

object Injection  {
    fun provideRepository(context: Context): EventRepository {
        val apiService = ApiConfig.getApiService()
        val database = FavoriteDatabase.getDatabase(context)
        val dao = database.favoriteDao()
        return EventRepository.getInstance(apiService, dao)
    }

}