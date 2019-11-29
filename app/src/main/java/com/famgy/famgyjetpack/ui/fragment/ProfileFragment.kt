package com.famgy.famgyjetpack.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.databinding.FragmentProfileBinding
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

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


        btn_profile_next.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileToCredentials())
        }

        //setOnclickListener会改变button可以点击，因此需要重新初始化状态
        btn_profile_next.setClickable(false)

        et_nickname.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ("".equals(et_nickname.text.toString())) {
                    btn_profile_next.setClickable(false)
                    btn_profile_next.setAlpha(0.5f)
                } else {
                    btn_profile_next.setClickable(true)
                    btn_profile_next.setAlpha(1.0f)
                }
            }
        })
    }
}