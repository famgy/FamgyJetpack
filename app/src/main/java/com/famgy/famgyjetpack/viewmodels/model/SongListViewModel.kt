package com.famgy.famgyjetpack.viewmodels.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.famgy.famgyjetpack.data.db.tb.Song
import com.famgy.famgyjetpack.data.repository.SongRepository

class SongListViewModel internal constructor(songRepository: SongRepository) : ViewModel() {

    val songs: LiveData<List<Song>> = songRepository.getAllSongs()
}