package com.nick.lib_seek_book.source

import com.nick.lib_seek_book.bean.Book
import com.nick.lib_seek_book.bean.BookDetail
import com.nick.lib_seek_book.bean.Chapters
import org.jsoup.nodes.Element
import java.net.URLDecoder

/**
 *  Nick in 2020/10/26 22:50
 *  Des:
 */
class BiQuGeSource : BaseSource() {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BiQuGeSource()
        }
    }

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

    override fun fetchSearchBook(baseSource: BaseSource, ele: Element): MutableList<Book> {
        val bookList = mutableListOf<Book>();
        for (bookEle in ele.select("tr")) {
            val itemList = bookEle.select("td");
            if (itemList.size >= 6) {
                val book = Book(
                    baseSource,
                    itemList[0].text(),
                    itemList[2].text(),
                    itemList[1].text(),
                    itemList[4].text(),
                    itemList[5].text(),
                    "${getSourceUrl()}/${itemList[0].select("a")[0].attr("href")}"
                );
                bookList.add(book)
            }
        }
        return bookList
    }

    override fun fetchBookDetail(book: Book, doc: Element): BookDetail {
        val bookrackList = mutableListOf<Chapters>()
        for (item in doc.select("dd")) {
            var bookrack = Chapters(
                book,
                item.text(),
                "${book.bookDetailUrl}/${item.select("a")[0].attr("href")}"
            )
            bookrackList.add(bookrack)
        }

        val bookDetail = BookDetail(
            book,
            doc.getElementById("intro").text(),
            doc.getElementById("fmimg").select("img").attr("src"),
            bookrackList
        );
        return bookDetail
    }

    override fun fetchContent(doc: Element): String {
        return doc.getElementById("content").toString()
    }


}