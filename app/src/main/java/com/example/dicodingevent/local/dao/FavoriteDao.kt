package com.example.dicodingevent.data.local.dao

import com.example.dicodingevent.data.local.entity.FavoriteEvent
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteEvent: FavoriteEvent)

    @Delete
    fun delete(favoriteEvent: FavoriteEvent)

    @Query("SELECT * FROM FavoriteEvent ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<FavoriteEvent>>

    @Query("SELECT * FROM FavoriteEvent WHERE id = :id")
    fun getFavoriteEventById(id: String): LiveData<FavoriteEvent>
}