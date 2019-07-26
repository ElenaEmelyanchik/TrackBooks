package com.example.trackbooks.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trackbooks.entity.Book
import io.reactivex.Completable

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book): Completable

    @Update
    fun updateBook(book: Book): Completable

    @Query("DELETE FROM Book WHERE id = :id")
    fun deleteBook(id: Int)

    @Query("SELECT * FROM Book")
    fun getBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM Book WHERE id = :id")
    fun getBook(id: Int): LiveData<Book>
}