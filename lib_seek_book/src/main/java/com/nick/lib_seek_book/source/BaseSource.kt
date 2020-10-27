package com.nick.lib_seek_book.source

import com.nick.lib_seek_book.bean.Book
import org.jsoup.nodes.Element

/**
 *  Nick in 2020/10/26 22:47
 *  Des: 书源
 */
abstract class BaseSource {

    abstract fun getSourceName():String

    abstract fun searchUrl(bookName:String):String

    abstract fun getSourceUrl(): String
    abstract fun bookIntroduce(bookName: String): String
    abstract fun bookChapter(bookName: String, bookCapter: String): String

    /**
     * 解析搜索目录
     */
    abstract fun fetchBookrack(doc: Element): MutableList<Book>

}