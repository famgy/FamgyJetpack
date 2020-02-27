package com.famgy.famgyjetpack.ui.activity


import androidx.annotation.NonNull;
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.famgy.famgyjetpack.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val mainViewModel = ViewModelProviders.of(this, MainModelFactory(RepositoryManager.providerUserRepository(applicationContext))).get(MainViewModel::class.java)

/*        val sp = getSharedPreferences(TABLE_PREFS, Context.MODE_PRIVATE)
        val ed = sp.edit()
        if (sp.getBoolean(SP_LOGINFO_INFO, false)) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, WorkstationFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, NavWelcomeFragment()).commit()

            ed.putBoolean(SP_LOGINFO_INFO, true).commit()
        }*/
    }
}
