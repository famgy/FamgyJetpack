package com.famgy.famgyjetpack.data.repository

import com.famgy.famgyjetpack.data.db.dao.SongDao

class SongRepository private constructor(private val songDao: SongDao){

    fun getAllSongs() = songDao.getAllSongs()

    fun getSong(songId: String) = songDao.getSong(songId)

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: SongRepository? = null

        fun getInstance(songDao: SongDao) =
            instance ?: synchronized(this) {
                instance ?: SongRepository(songDao).also { instance = it }
            }
    }
}