package com.famgy.famgyjetpack.data.repository

import androidx.lifecycle.LiveData
import com.famgy.famgyjetpack.data.db.dao.UserDao
import com.famgy.famgyjetpack.data.db.tb.User

class UserRepository(private val userDao: UserDao) {

    fun getLoginInfo(account: String, passwd: String): LiveData<User?> = userDao.queryUser(account, passwd)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
    }
}