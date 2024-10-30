package com.example.dicodingevent.data.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.dicodingevent.data.local.entity.FavoriteEvent
import com.example.dicodingevent.data.local.dao.FavoriteDao
import com.example.dicodingevent.data.controller.response.Event
import com.example.dicodingevent.data.controller.response.ListEventsItem
import com.example.dicodingevent.data.controller.retrofit.ApiService

class EventRepository private constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
){

    fun getAllFavorite(): LiveData<List<FavoriteEvent>> {
        return favoriteDao.getAllFavorite()
    }

    fun loadFinishedEvents(): LiveData<Result<List<ListEventsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFinished()
            val events = response.listEvents
            val eventList = events.map { events ->
                ListEventsItem(
                    events.summary,
                    events.mediaCover,
                    events.registrants,
                    events.imageLogo,
                    events.link,
                    events.description,
                    events.ownerName,
                    events.cityName,
                    events.quota,
                    events.name,
                    events.id,
                    events.beginTime,
                    events.endTime,
                    events.category
                )
            }
            emit(Result.Success(eventList))
        } catch (e: Exception) {
            Log.d("EventRepository", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun loadUpcomingEvents() : LiveData<Result<List<ListEventsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUpcoming()
            val events = response.listEvents
            val eventList = events.map { events ->
                ListEventsItem(
                    events.summary,
                    events.mediaCover,
                    events.registrants,
                    events.imageLogo,
                    events.link,
                    events.description,
                    events.ownerName,
                    events.cityName,
                    events.quota,
                    events.name,
                    events.id,
                    events.beginTime,
                    events.endTime,
                    events.category
                )
            }
            emit(Result.Success(eventList))
        } catch (e: Exception) {
            Log.d("EventRepository", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUpcoming5() : LiveData<Result<List<ListEventsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUpcoming5()
            val events = response.listEvents
            val eventList = events.map { events ->
                ListEventsItem(
                    events.summary,
                    events.mediaCover,
                    events.registrants,
                    events.imageLogo,
                    events.link,
                    events.description,
                    events.ownerName,
                    events.cityName,
                    events.quota,
                    events.name,
                    events.id,
                    events.beginTime,
                    events.endTime,
                    events.category
                )
            }
            emit(Result.Success(eventList))
        } catch (e: Exception) {
            Log.d("EventRepository", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFinished5() : LiveData<Result<List<ListEventsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFinished5()
            val events = response.listEvents
            val eventList = events.map { events ->
                ListEventsItem(
                    events.summary,
                    events.mediaCover,
                    events.registrants,
                    events.imageLogo,
                    events.link,
                    events.description,
                    events.ownerName,
                    events.cityName,
                    events.quota,
                    events.name,
                    events.id,
                    events.beginTime,
                    events.endTime,
                    events.category
                )
            }
            emit(Result.Success(eventList))
        } catch (e: Exception) {
            Log.d("EventRepository", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun detailEvent(id: String): LiveData<Result<Event>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getEvent(id)
            val event = response.event
            emit(Result.Success(event))
        } catch (e: Exception) {
            Log.d("EventRepository", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: EventRepository? = null
        fun getInstance(
            apiService: ApiService,
            favoriteDao: FavoriteDao,
        ): EventRepository =
            instance ?: synchronized(this) {
                instance ?: EventRepository(apiService, favoriteDao)
            }.also { instance = it }
    }
}
