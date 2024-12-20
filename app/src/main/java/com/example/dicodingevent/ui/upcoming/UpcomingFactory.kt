package com.example.dicodingevent.ui.upcoming

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.data.controller.EventRepository
import com.example.dicodingevent.di.Injection

class UpcomingFactory private constructor(private val eventRepository: EventRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingViewModel::class.java)) {
            return UpcomingViewModel(eventRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: UpcomingFactory? = null
        fun getInstance(context: Context): UpcomingFactory =
            instance ?: synchronized(this) {
                instance ?: UpcomingFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}
