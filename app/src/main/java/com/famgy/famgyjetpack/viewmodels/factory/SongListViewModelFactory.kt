
package com.famgy.famgyjetpack.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.famgy.famgyjetpack.data.repository.SongRepository
import com.famgy.famgyjetpack.viewmodels.model.SongListViewModel


/**
 * Factory for creating a [SongListViewModel] with a constructor that takes a [SongRepository].
 */
class SongListViewModelFactory(
    private val repository: SongRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = SongListViewModel(repository) as T
}
