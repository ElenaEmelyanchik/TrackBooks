package com.example.trackbooks.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Book(
    val title: String,
    val author: String,
    val isbn: String,
    val numberPages: Int,
    val rate: Int
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
