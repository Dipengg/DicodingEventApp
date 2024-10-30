package com.example.dicodingevent.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.data.controller.EventRepository
import com.example.dicodingevent.di.Injection

class DetailFactory private constructor(private val eventRepository: EventRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(eventRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: DetailFactory? = null
        fun getInstance(context: Context): DetailFactory =
            instance ?: synchronized(this) {
                instance ?: DetailFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}