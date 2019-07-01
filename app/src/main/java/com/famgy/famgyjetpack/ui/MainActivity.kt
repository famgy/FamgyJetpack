package com.famgy.famgyjetpack.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.famgy.famgyjetpack.R
import com.famgy.famgyjetpack.ui.login.WelcomeFragment
import com.famgy.famgyjetpack.ui.workstation.WorkstationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel = ViewModelProviders.of(this, MainModelFactory()).get(MainViewModel::class.java)
        if (viewModel.getLoginCatch() != true) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, WelcomeFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, WorkstationFragment()).commit()
        }
    }
}
