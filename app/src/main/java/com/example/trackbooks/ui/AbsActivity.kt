package com.example.trackbooks.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.trackbooks.mvvm.BookViewModel
import com.example.trackbooks.mvvm.BookViewModelFactory
import com.example.trackbooks.R
import javax.inject.Inject

abstract class AbsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: BookViewModelFactory

    val viewModel: BookViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(BookViewModel::class.java)
    }

    abstract fun inject()
    abstract fun successObservableCallback(state: BookViewModel.State.Success)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setupObservables()
    }

    open fun setupObservables() {
        viewModel.state().observe(this, Observer {
            when (it) {
                is BookViewModel.State.Success -> successObservableCallback(it)
                is BookViewModel.State.Error -> displayToast(it.message ?: getString(R.string.unknown_error))
            }
        })
    }

    fun displayToast(message: String): Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
}