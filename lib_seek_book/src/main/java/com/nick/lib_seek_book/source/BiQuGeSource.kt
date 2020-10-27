package com.nick.lib_seek_book.source

import com.nick.lib_core.utils.logD
import com.nick.lib_seek_book.bean.Book
import org.jsoup.nodes.Element
import java.net.URLDecoder

/**
 *  Nick in 2020/10/26 22:50
 *  Des:
 */
class BiQuGeSource : BaseSource() {


    override fun getSourceName(): String {
        return "笔趣阁"
    }

    override fun getSourceUrl(): String {
        return "http://www.biquge.info/"
    }

    override fun searchUrl(bookName: String): String {
        return "${getSourceUrl()}modules/article/search.php?searchkey=" + URLDecoder.decode(
            bookName,
            "utf-8"
        )
    }

    override fun bookIntroduce(bookName: String): String {
        return "${getSourceUrl()}${bookName}/"
    }

    override fun bookChapter(bookName: String, bookCapter: String): String {
        return "${getSourceUrl()}${bookName}/${bookCapter}.html"
    }

    override fun fetchBookrack(ele: Element): MutableList<Book> {
        val bookList = mutableListOf<Book>();
        for (bookEle in ele.select("tr")) {
            val itemList = bookEle.select("td");
            logD("fetchBookrack:size${itemList.size} _____ $itemList")
            if (itemList.size >= 6) {
                val book = Book(
                    itemList[0].text(),
                    itemList[2].text(),
                    itemList[1].text(),
                    itemList[4].text(),
                    itemList[5].text(),
                    getSourceUrl() + itemList[0].attr("href")
                );
                bookList.add(book)
            }
        }
        return bookList
    }


}