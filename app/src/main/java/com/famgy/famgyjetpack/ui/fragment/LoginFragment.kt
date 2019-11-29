package com.famgy.famgyjetpack.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.R
import com.famgy.famgyjetpack.databinding.FragmentLoginBinding
import com.famgy.famgyjetpack.utilities.Exit_TIME
import com.famgy.famgyjetpack.utilities.ScreenInfoUtil
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.famgy.famgyjetpack.viewmodels.model.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {

    private var exitTime = 0L
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

        var window = getActivity()!!.getWindow()

        //全透明
        window.setStatusBarColor(Color.TRANSPARENT)
        //window.setNavigationBarColor(Color.TRANSPARENT)

        //半透明
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);



        var layoutParams: RelativeLayout.LayoutParams = ll_third_login.getLayoutParams() as RelativeLayout.LayoutParams;
        layoutParams.bottomMargin = ScreenInfoUtil.getNavigationBarHeight(context);
        ll_third_login.setLayoutParams(layoutParams);


        ll_third_login.


        tv_register.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginToRegistration())
        }

        ib_password_change.setOnClickListener {
            if (et_password.inputType == 129) {
                et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                ib_password_change.setImageResource(R.drawable.ic_password_show)
            } else if (et_password.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                et_password.setInputType(129)
                ib_password_change.setImageResource(R.drawable.ic_password_hide)
            }
        }

        val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> navController.popBackStack()
                INVALID_AUTHENTICATION ->
                    Snackbar.make(
                        view,
                        "账号或密码不正确",
                        Snackbar.LENGTH_SHORT
                    ).show()
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

}