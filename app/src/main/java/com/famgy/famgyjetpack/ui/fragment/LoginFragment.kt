package com.famgy.famgyjetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.R
import com.famgy.famgyjetpack.databinding.FragmentLoginBinding
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.model = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginToRegistration())
        }

        val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> navController.popBackStack()
                INVALID_AUTHENTICATION ->
                    Snackbar.make(view,
                        "账号或密码不正确",
                        Snackbar.LENGTH_SHORT
                    ).show()
            }
        })
    }
}