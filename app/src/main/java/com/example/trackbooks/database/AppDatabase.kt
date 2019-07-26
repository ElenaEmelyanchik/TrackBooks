package com.example.trackbooks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackbooks.entity.Book

@Database(entities = [(Book::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}