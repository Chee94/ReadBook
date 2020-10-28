package com.nick.lib_seek_book.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Nick in 2020/10/26 22:14
 *  Des:
 */
@Parcelize
data class Chapters(var book: Book, var name: String, var url: String) : Parcelable