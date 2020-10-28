package com.nick.lib_seek_book.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Nick in 2020/10/28 22:15
 *  Des:
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class BookDetailLocal(var book: BookDetail, var lruChapters: Chapters, var position:Int):Parcelable