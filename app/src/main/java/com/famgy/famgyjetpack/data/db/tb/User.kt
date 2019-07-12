package com.famgy.famgyjetpack.data.db.tb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "account") val account: String?,
    @ColumnInfo(name = "passwd") val passwd: String?,
    @ColumnInfo(name = "name") val name: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
