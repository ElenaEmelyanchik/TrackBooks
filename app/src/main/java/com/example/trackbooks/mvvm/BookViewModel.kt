package com.example.trackbooks.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trackbooks.BookRepository
import com.example.trackbooks.R
import com.example.trackbooks.entity.Book
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class BookViewModel(val repository: BookRepository) : ViewModel() {

    private val isSuccessful = MutableLiveData<Boolean>()
    private val state = MutableLiveData<State>()
    private val compositeDisposable = CompositeDisposable()

    sealed class State {
        class Success(val resId: Int) : State()
        class Error(val message: String?) : State()
    }

    fun addBook(
        title: String,
        author: String,
        isbn: String,
        pages: Int,
        rating: Int
    ) {
        compositeDisposable.add(
            repository.addBook(generateBook(title, author, isbn, pages, rating))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    state.value =
                        State.Success(R.string.book_success_add)
                }
        )

    }

    fun editBook(
        id: Int?,
        title: String,
        author: String,
        isbn: String,
        pages: Int,
        rating: Int
    ) {
        compositeDisposable.add(
            repository.updateBook(
                generateBook(
                    title,
                    author,
                    isbn,
                    pages,
                    rating,
                    id
                )
            ).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    state.value =
                        State.Success(R.string.book_success_update)
                }
        )

    }

    private fun generateBook(
        title: String,
        author: String,
        isbn: String,
        pages: Int,
        rating: Int,
        id: Int? = null
    ) = Book(
        title = title,
        author = author,
        isbn = isbn,
        numberPages = pages,
        rate = rating
    ).apply { if (id != null) this.id = id }

    fun getBooks() = repository.getBookList()

    fun getBookById(id: Int) = repository.getBook(id)

    fun state() = state

    fun deleteBook(id: Int) {
        Completable.fromAction { repository.deleteBook(id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    state.value =
                        State.Success(R.string.book_success_delete)
                }

                override fun onError(e: Throwable) {
                    state.value = State.Error(e.message)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}