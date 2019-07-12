package com.famgy.famgyjetpack.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.R
import com.famgy.famgyjetpack.databinding.FragmentLoginBinding
import com.famgy.famgyjetpack.databinding.FragmentMainBinding
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.UNAUTHENTICATED

class MainFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.model = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (viewModel.authenticationState.value) {
            AUTHENTICATED -> showWelcomeMessage()
            UNAUTHENTICATED -> findNavController().navigate(MainFragmentDirections.actionMainToLogin())
        }
    }

    private fun showWelcomeMessage() {
        Log.e("TEST", "+++++++++++++++")
    }
}