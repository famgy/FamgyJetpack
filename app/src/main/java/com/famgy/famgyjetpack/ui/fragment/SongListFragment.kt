package com.famgy.famgyjetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.famgy.famgyjetpack.adapters.SongAdapter
import com.famgy.famgyjetpack.databinding.FragmentMusicListBinding
import com.famgy.famgyjetpack.utilities.InjectorUtils
import com.famgy.famgyjetpack.viewmodels.model.SongListViewModel


class SongListFragment : Fragment() {

    private val viewModel: SongListViewModel by viewModels {
        InjectorUtils.provideSongListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMusicListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = SongAdapter()
        binding.musicList.adapter = adapter

        viewModel.songs.observe(viewLifecycleOwner) { songs ->
            adapter.submitList(songs)
        }

        return binding.root
    }
}