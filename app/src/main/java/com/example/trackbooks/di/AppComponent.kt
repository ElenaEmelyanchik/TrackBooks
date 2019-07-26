package com.example.trackbooks.di

import com.example.trackbooks.BookRepository
import com.example.trackbooks.database.BookDao
import com.example.trackbooks.ui.AddBookActivity
import com.example.trackbooks.ui.EditBookActivity
import com.example.trackbooks.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, RoomModule::class])
interface AppComponent {

    fun bookDao(): BookDao
    fun bookRepository(): BookRepository
    fun inject(activity: MainActivity)
    fun inject(activity: AddBookActivity)
    fun inject(activity: EditBookActivity)
}