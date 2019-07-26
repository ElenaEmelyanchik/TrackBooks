package com.example.trackbooks.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.trackbooks.entity.Book
import kotlinx.android.synthetic.main.simple_list_item.view.*


class BookAdapter(
    val context: Context,
    val item: Int,
    val bookList: List<Book>,
    val editCallback: (Int) -> Unit,
    val deleteCallback: (Int) -> Unit
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(item, parent, false)

        val book = getItem(position)
        view.title.text = book.title
        view.author.text = book.author
        view.isbn.text = book.isbn
        view.pages.text = book.numberPages.toString()
        view.rating.rating = book.rate.toFloat()

        view.edit.setOnClickListener {
            editCallback(book.id)
        }
        view.delete.setOnClickListener {
            deleteCallback(book.id)
        }
        return view

    }

    override fun getItem(position: Int): Book {
        return bookList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return bookList.get(position).id.toLong()
    }

    override fun getCount(): Int {
        return bookList.size
    }
}