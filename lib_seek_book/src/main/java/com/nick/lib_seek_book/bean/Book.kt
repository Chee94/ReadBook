package com.nick.lib_seek_book.bean

import android.os.Parcelable
import com.nick.lib_seek_book.source.BaseSource
import kotlinx.android.parcel.Parcelize

/**
 *  Nick in 2020/10/26 22:14
 *  Des:
 */
//书名 作者 最新章节 最近更新日期
@Parcelize
data class Book(
    var baseSource: BaseSource,
    var name: String,
    var author: String,
    var newChapters: String,
    var updateDate: String,
    var state: String,
    var bookDetailUrl: String
): Parcelable