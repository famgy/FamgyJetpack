package com.famgy.famgyjetpack.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.R
import com.famgy.famgyjetpack.adapters.HomeViewPagerAdapter
import com.famgy.famgyjetpack.adapters.MY_GARDEN_PAGE_INDEX
import com.famgy.famgyjetpack.adapters.PLANT_LIST_PAGE_INDEX
import com.famgy.famgyjetpack.adapters.SONG_LIST_PAGE_INDEX
import com.famgy.famgyjetpack.databinding.FragmentHomeViewPagerBinding
import com.famgy.famgyjetpack.utilities.Exit_TIME
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel.AuthenticationState.UNAUTHENTICATED
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    private var exitTime = 0L
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomeViewPagerAdapter(this)

        //Traversing the viewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var window = activity!!.getWindow()

        //xml layout add : android:fitsSystemWindows="true"
        window.setStatusBarColor(Color.TRANSPARENT)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        val navController = findNavController()
        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> showWelcomeMessage()
                UNAUTHENTICATED -> navController.navigate(HomeViewPagerFragmentDirections.actionMainToLogin())
                INVALID_AUTHENTICATION -> navController.navigate(HomeViewPagerFragmentDirections.actionMainToLogin())
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if ((System.currentTimeMillis() - exitTime) > Exit_TIME) {
                Toast.makeText(context, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis()
            } else {
                activity!!.finish()
            }
        }
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            SONG_LIST_PAGE_INDEX -> R.drawable.song_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            SONG_LIST_PAGE_INDEX -> getString(R.string.song_list_title)
            else -> null
        }
    }

    private fun showWelcomeMessage() {
        Log.e("TEST", "+++++++ Welcome ++++++++")
    }
}