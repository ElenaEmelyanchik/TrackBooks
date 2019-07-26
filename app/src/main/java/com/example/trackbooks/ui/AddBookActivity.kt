package com.example.trackbooks.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.trackbooks.App
import com.example.trackbooks.IsbnValidator
import com.example.trackbooks.R
import com.example.trackbooks.mvvm.BookViewModel
import kotlinx.android.synthetic.main.activity_details_book.author
import kotlinx.android.synthetic.main.activity_details_book.isbn
import kotlinx.android.synthetic.main.activity_details_book.pages
import kotlinx.android.synthetic.main.activity_details_book.button_add_book as add
import kotlinx.android.synthetic.main.activity_details_book.ratingBar as rating
import kotlinx.android.synthetic.main.activity_details_book.title as titleBook

class AddBookActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)
        setupListeners()
    }

    override fun inject() {
        (application as App).component().inject(this)
    }

    override fun successObservableCallback(state: BookViewModel.State.Success) {
        finish()
        displayToast(getString(state.resId))
    }

    private fun setupListeners() {
        add.setOnClickListener {
            if (isbnValid(isbn.text.toString())) {
                viewModel.addBook(
                    titleBook.text.toString(),
                    author.text.toString(),
                    isbn.text.toString(),
                    pages.text.toString().toInt(),
                    rating.rating.toInt()
                )
            } else {
                isbn.error = getString(R.string.invalid_isbn)
            }
        }
    }

    private fun isbnValid(isbn: String): Boolean {
        return when {
            isbn.length == 13 -> IsbnValidator.validateIsbn13(isbn)
            isbn.length == 10 -> IsbnValidator.validateIsbn10(isbn)
            else -> false
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, AddBookActivity::class.java).apply {
                context.startActivity(this)
            }

        }
    }
}