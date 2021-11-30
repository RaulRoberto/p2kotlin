package com.example.lista.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lista.model.Item

@Database(entities = arrayOf(Item::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): Dao

}