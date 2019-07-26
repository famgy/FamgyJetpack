package com.famgy.famgyjetpack.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.famgy.famgyjetpack.R
import kotlinx.android.synthetic.main.fragment_credentials.*
import kotlinx.android.synthetic.main.fragment_profile.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CredentialsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CredentialsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CredentialsFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credentials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_credentials_sure.setOnClickListener {
            findNavController().navigate(CredentialsFragmentDirections.actionCredentialsToLogin())
        }

        //setOnclickListener会改变button可以点击，因此需要重新初始化状态
        btn_credentials_sure.setClickable(false)

        et_credentials_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_credentials_password.text.length < 4) {
                    btn_credentials_sure.setClickable(false)
                    btn_credentials_sure.setAlpha(0.5f)
                } else {
                    btn_credentials_sure.setClickable(true)
                    btn_credentials_sure.setAlpha(1.0f)
                }
            }
        })
    }
}
