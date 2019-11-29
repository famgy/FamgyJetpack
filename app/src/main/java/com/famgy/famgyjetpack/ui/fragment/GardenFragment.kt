package com.famgy.famgyjetpack.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.famgy.famgyjetpack.adapters.GardenPlantingAdapter
import com.famgy.famgyjetpack.databinding.FragmentGardenBinding
import com.famgy.famgyjetpack.utilities.InjectorUtils
import com.famgy.famgyjetpack.viewmodels.model.GardenPlantingListViewModel
import java.util.logging.Logger

class GardenFragment : Fragment() {

    private lateinit var binding: FragmentGardenBinding
    private val viewModel: GardenPlantingListViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            binding.hasPlantings = !result.isNullOrEmpty()

            Logger.getLogger("====GardenFragment====").info("subscribeUi of adapter is submitList : \n" + result)
            adapter.submitList(result)
        }
    }
}