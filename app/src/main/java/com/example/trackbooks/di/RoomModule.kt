package com.example.trackbooks.di

import android.content.Context
import androidx.room.Room
import com.example.trackbooks.database.AppDatabase
import com.example.trackbooks.database.BookDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Book").build()
    }

    @Singleton
    @Provides
    fun provideBookDao(database: AppDatabase): BookDao {
        return database.bookDao()
    }
}