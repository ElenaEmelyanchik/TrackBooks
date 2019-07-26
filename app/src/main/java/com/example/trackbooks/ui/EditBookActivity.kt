package com.example.trackbooks.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.trackbooks.App
import com.example.trackbooks.R
import com.example.trackbooks.mvvm.BookViewModel
import kotlinx.android.synthetic.main.activity_details_book.author
import kotlinx.android.synthetic.main.activity_details_book.isbn
import kotlinx.android.synthetic.main.activity_details_book.pages
import kotlinx.android.synthetic.main.activity_details_book.button_add_book as add
import kotlinx.android.synthetic.main.activity_details_book.ratingBar as rating
import kotlinx.android.synthetic.main.activity_details_book.title as titleBook


class EditBookActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)
        val id = intent?.extras?.getInt(EXTRA_BOOK_ID)
        setupObservables(id)
        setupListeners(id)
    }

    private fun setupListeners(id: Int?) {
        add.setOnClickListener {
            viewModel.editBook(
                id,
                titleBook.text.toString(),
                author.text.toString(),
                isbn.text.toString(),
                pages.text.toString().toInt(),
                rating.rating.toInt()
            )
        }
    }

    fun setupObservables(id: Int?) {
        super.setupObservables()
        if (id != null) {
            viewModel.getBookById(id).observe(this, Observer {
                titleBook.setText(it.title)
                author.setText(it.author)
                isbn.setText(it.isbn)
                pages.setText(it.numberPages.toString())
                rating.rating = it.rate.toFloat()
            })
        }
    }

    override fun inject() {
        (application as App).component().inject(this)
    }

    override fun successObservableCallback(state: BookViewModel.State.Success) {
        finish()
        displayToast(getString(state.resId))
    }

    companion object {
        fun start(context: Context, id: Int) {
            Intent(context, EditBookActivity::class.java).apply {
                putExtra(EXTRA_BOOK_ID, id)
                context.startActivity(this)
            }
        }

        const val EXTRA_BOOK_ID = "ID"
    }
}