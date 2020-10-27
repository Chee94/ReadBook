package com.nick.lib_seek_book.bean

import java.io.Serializable

/**
 *  Nick in 2020/10/27 22:34
 *  Des:
 */

data class BookDetail(var book: Book, var introduce: String,var imgUrl:String, var bookrackList: MutableList<Bookrack>):
    Serializable