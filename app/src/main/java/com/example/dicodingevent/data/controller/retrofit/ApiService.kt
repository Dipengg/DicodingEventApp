package com.example.dicodingevent.data.controller.retrofit

import com.example.dicodingevent.data.controller.response.DetailResponse
import com.example.dicodingevent.data.controller.response.ListEventsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/events")
    suspend fun getFinished(@Query("active") active: Int = 0): ListEventsResponse

    @GET("/events")
    suspend fun getUpcoming(@Query("active") active: Int = 1): ListEventsResponse

    @GET("/events")
    suspend fun getUpcoming5(@Query("active") active: Int = 1,
                             @Query("limit") limit: Int = 5
    ): ListEventsResponse

    @GET("/events")
    suspend fun getFinished5(@Query("active") active: Int = 0,
                             @Query("limit") limit: Int = 5
    ): ListEventsResponse

    @GET("/events/{id}")
    suspend fun getEvent(@Path("id") id: String): DetailResponse

    @GET("/events")
    suspend fun searchEvents(@Query("q") query: String): ListEventsResponse
}