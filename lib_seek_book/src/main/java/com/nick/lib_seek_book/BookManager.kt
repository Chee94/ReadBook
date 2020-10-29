package com.nick.lib_seek_book

import androidx.appcompat.app.AppCompatActivity
import com.nick.lib_core.utils.safe.MD5
import com.nick.lib_seek_book.bean.BookDetail
import com.nick.lib_seek_book.bean.BookDetailLocal
import com.nick.lib_seek_book.bean.Chapters
import com.nick.lib_seek_book.bean.ChaptersDetail
import com.tencent.mmkv.MMKV
import io.reactivex.rxjava3.functions.Consumer

/**
 *  Nick in 2020/10/28 0:42
 *  Des: 管理当前阅读书本
 */
class BookManager {

    var bookrackList = mutableListOf<Chapters>()
    var currentBookrack = 0;//当前阅读章节
    var act: AppCompatActivity? = null
    var bookKv = MMKV.mmkvWithID("book_bookshelf");
    var bookDetail: BookDetail? = null

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BookManager()
        }
    }

    fun setBookrackList(bookDetail: BookDetail, act: AppCompatActivity): BookManager {
        this.bookDetail = bookDetail
        this.bookrackList.clear()
        this.bookrackList.addAll(bookDetail.chaptersList)
        this.act
        return this
    }

    fun current(callback: Consumer<ChaptersDetail>, act: AppCompatActivity) {
        bookrackList?.let {
            val chapters = bookrackList[currentBookrack]
            SeekBook.instance.getBookrackContent(chapters, act, callback)
            update(chapters)
        }
    }

    //下一页
    fun next(callback: Consumer<ChaptersDetail>, act: AppCompatActivity) {
        bookrackList?.let {
            val chapters = bookrackList[++currentBookrack]
            SeekBook.instance.getBookrackContent(chapters, act, callback)
            update(chapters)
        }
    }

    //上一页
    fun last(callback: Consumer<ChaptersDetail>, act: AppCompatActivity) {
        bookrackList?.let {
            val chapters = bookrackList[--currentBookrack]
            SeekBook.instance.getBookrackContent(chapters, act, callback)
            update(chapters)
        }
    }

    /**
     * 更新进度
     */
    fun update(chapters: Chapters) {
        val bookDetailLocal = BookDetailLocal(bookDetail!!, chapters, currentBookrack)
        bookKv.remove(MD5.encode(chapters.book.bookDetailUrl))
        bookKv.encode(MD5.encode(chapters.book.bookDetailUrl), bookDetailLocal)
    }

    /**
     * 添加到书架
     */
    fun addBookshelf(callbak: (Boolean) -> Int) {
        if (bookrackList.size == 0) {
            callbak(false)
        } else {
            update(bookrackList.get(0))
            callbak(true)
        }
    }

    fun lruPosition(position: Int): BookManager {
        this.currentBookrack = position
        return this
    }

}