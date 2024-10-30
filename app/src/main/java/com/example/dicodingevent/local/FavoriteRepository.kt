package com.example.dicodingevent.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.dicodingevent.data.local.entity.FavoriteEvent
import com.example.dicodingevent.data.local.dao.FavoriteDao
import com.example.dicodingevent.data.local.dao.FavoriteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private lateinit var favoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        favoriteDao = db.favoriteDao()
    }

    fun getAllFavEvent(): LiveData<List<FavoriteEvent>> {
        return favoriteDao.getAllFavorite()
    }

    fun insert(favoriteEvent: FavoriteEvent) {
        executorService.execute { favoriteDao.insert(favoriteEvent) }
    }

    fun delete(favoriteEvent: FavoriteEvent) {
        executorService.execute { favoriteDao.delete(favoriteEvent) }
    }

    fun getFavoriteEventById(id: String): LiveData<FavoriteEvent> {
        return favoriteDao.getFavoriteEventById(id)
    }
}