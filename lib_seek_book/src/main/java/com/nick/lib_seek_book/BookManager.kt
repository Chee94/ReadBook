package com.nick.lib_seek_book

import com.nick.lib_seek_book.bean.Bookrack
import com.nick.lib_seek_book.bean.BookrackDetail
import io.reactivex.rxjava3.functions.Consumer

/**
 *  Nick in 2020/10/28 0:42
 *  Des:
 */
class BookManager {

    var bookrackList = mutableListOf<Bookrack>()
    var currentBookrack = 0;

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BookManager()
        }
    }

    fun setBookrackList(list: MutableList<Bookrack>): BookManager {
        this.bookrackList.clear()
        this.bookrackList.addAll(list)
        return this
    }

    fun next(callback: Consumer<BookrackDetail>) {
        bookrackList?.let {
            SeekBook.instance.getBookrackContent(bookrackList[currentBookrack++], callback)
        }
    }

    fun reset(): BookManager {
        currentBookrack = 0
        return this
    }

}