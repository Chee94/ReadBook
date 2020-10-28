package com.nick.lib_seek_book.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Nick in 2020/10/28 0:59
 *  Des:
 */
@Parcelize
data class ChaptersDetail(var name: String, var content: String) : Parcelable