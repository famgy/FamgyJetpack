package com.famgy.famgyjetpack.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.famgy.famgyjetpack.data.db.tb.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User):Long

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE account = :account AND passwd = :passwd")
    fun queryUser(account:String, passwd:String): LiveData<User?>
}