package com.famgy.famgyjetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.databinding.FragmentProfileBinding
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.famgy.famgyjetpack.viewmodel.model.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileToCredentials())
        }
    }
}