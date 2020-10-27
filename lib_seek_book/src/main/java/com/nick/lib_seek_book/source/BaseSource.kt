package com.nick.lib_seek_book.source

import com.nick.lib_seek_book.bean.Book
import com.nick.lib_seek_book.bean.BookDetail
import org.jsoup.nodes.Element
import java.io.Serializable

/**
 *  Nick in 2020/10/26 22:47
 *  Des: 书源
 */
abstract class BaseSource : Serializable {

    abstract fun getSourceName(): String

    abstract fun searchUrl(bookName: String): String

    abstract fun getSourceUrl(): String
    abstract fun bookIntroduce(bookName: String): String
    abstract fun bookChapter(bookName: String, bookCapter: String): String

    /**
     * 解析搜索目录
     */
    abstract fun fetchSearchBook(baseSource: BaseSource, doc: Element): MutableList<Book>

    /**
     * 解析书详情
     */
    abstract fun fetchBookDetail(ook:Book, doc: Element): BookDetail

    /**
     * 解析文章内容
     */
    abstract fun fetchContent(doc: Element):String


}