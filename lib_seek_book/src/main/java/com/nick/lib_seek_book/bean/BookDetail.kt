package com.nick.lib_seek_book.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Nick in 2020/10/27 22:34
 *  Des:
 */

@Parcelize
data class BookDetail(var book: Book, var introduce: String,var imgUrl:String, var chaptersList: MutableList<Chapters>):
    Parcelable