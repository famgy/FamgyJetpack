package com.famgy.famgyjetpack.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.famgy.famgyjetpack.data.db.tb.Plant
import com.famgy.famgyjetpack.data.db.tb.Song

@Dao
interface SongDao {

    @Query("SELECT * FROM song ORDER BY name")
    fun getAllSongs(): LiveData<List<Song>>

    @Query("SELECT * FROM song WHERE id = :songId")
    fun getSong(songId: String): LiveData<Song>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songs: List<Song>)
}