package com.example.dicodingevent.ui.finished

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.controller.EventRepository
import com.example.dicodingevent.data.controller.Result
import com.example.dicodingevent.data.controller.response.ListEventsItem

class FinishedViewModel(private val eventRepository: EventRepository) : ViewModel() {

    private val _events = MediatorLiveData<List<ListEventsItem>>()
    val events: LiveData<List<ListEventsItem>> get() = _events

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    companion object {
        private const val TAG = "FinishedViewModel"
    }

    fun loadFinishedEvents() {
        _isLoading.value = true

        val result = eventRepository.loadFinishedEvents()

        _events.addSource(result) { result ->
            when (result) {
                is Result.Loading -> _isLoading.value = true
                is Result.Success -> {
                    _isLoading.value = false
                    _events.value = result.data
                }
                is Result.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.error
                    Log.d(TAG, "Error fetching events: ${result.error}")
                }
            }
        }
    }



}