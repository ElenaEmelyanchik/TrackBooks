package com.example.trackbooks

import androidx.lifecycle.LiveData
import com.example.trackbooks.database.BookDao
import com.example.trackbooks.entity.Book
import javax.inject.Inject

class BookRepository @Inject constructor(val bookDao: BookDao) {

    fun getBookList(): LiveData<List<Book>> = bookDao.getBooks()

    fun getBook(id: Int): LiveData<Book> = bookDao.getBook(id)

    fun addBook(book: Book) = bookDao.insertBook(book)

    fun updateBook(book: Book) = bookDao.updateBook(book)

    fun deleteBook(id: Int) = bookDao.deleteBook(id)
}