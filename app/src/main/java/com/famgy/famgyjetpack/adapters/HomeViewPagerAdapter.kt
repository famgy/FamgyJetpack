package com.famgy.famgyjetpack.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.famgy.famgyjetpack.ui.fragment.GardenFragment
import com.famgy.famgyjetpack.ui.fragment.PlantListFragment
import com.famgy.famgyjetpack.ui.fragment.SongListFragment

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1
const val SONG_LIST_PAGE_INDEX = 2

class HomeViewPagerAdapter(fragment: Fragment) :  FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() },
        SONG_LIST_PAGE_INDEX to { SongListFragment() }
    )

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    override fun getItemCount() = tabFragmentsCreators.size
}