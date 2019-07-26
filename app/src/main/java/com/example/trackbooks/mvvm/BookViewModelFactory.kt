package com.example.trackbooks.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trackbooks.BookRepository
import javax.inject.Inject

class BookViewModelFactory @Inject constructor(val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookViewModel(repository) as T
    }
}