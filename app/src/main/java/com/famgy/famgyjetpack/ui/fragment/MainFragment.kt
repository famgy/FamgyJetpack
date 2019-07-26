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
import com.famgy.famgyjetpack.databinding.FragmentLoginBinding
import com.famgy.famgyjetpack.databinding.FragmentMainBinding
import com.famgy.famgyjetpack.util.Exit_TIME
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.UNAUTHENTICATED
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION

class MainFragment : Fragment() {

    private var exitTime = 0L
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

        var window = activity!!.getWindow()
        //window.setStatusBarColor(Color.)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> showWelcomeMessage()
                UNAUTHENTICATED -> navController.navigate(MainFragmentDirections.actionMainToLogin())
                INVALID_AUTHENTICATION -> navController.navigate(MainFragmentDirections.actionMainToLogin())
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

    private fun showWelcomeMessage() {
        Log.e("TEST", "+++++++++++++++")
    }
}