package com.example.trackbooks.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.trackbooks.App
import com.example.trackbooks.R
import com.example.trackbooks.mvvm.BookViewModel
import kotlinx.android.synthetic.main.activity_main.addBook
import kotlinx.android.synthetic.main.activity_main.list_books as listBooks

class MainActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
    }

    private fun setupListeners() {
        addBook.setOnClickListener { addNewBook() }
    }

    override fun setupObservables() {
        super.setupObservables()
        viewModel.getBooks().observe(this, Observer {
            val adapter = BookAdapter(
                this,
                R.layout.simple_list_item,
                it,
                { id -> EditBookActivity.start(this, id) },
                { id -> viewModel.deleteBook(id) })
            listBooks.adapter = adapter
        })
    }

    override fun inject() {
        (application as App).component().inject(this)
    }

    override fun successObservableCallback(state: BookViewModel.State.Success) {
        displayToast(getString(state.resId))
    }

    private fun addNewBook() {
        AddBookActivity.start(this)
    }
}
