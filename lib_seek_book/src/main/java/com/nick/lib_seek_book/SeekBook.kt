package com.nick.lib_seek_book

import com.nick.lib_core.utils.logD
import com.nick.lib_seek_book.bean.Book
import com.nick.lib_seek_book.bean.BookDetail
import com.nick.lib_seek_book.bean.Bookrack
import com.nick.lib_seek_book.source.BaseSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *  Nick in 2020/10/26 22:36
 *  Des:
 */
class SeekBook {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            SeekBook()
        }
    }


    fun searchBook(bookName: String, callback: Consumer<MutableList<Book>>) {
        Observable.create<BaseSource> {
            for (source in SourceList) {
                it.onNext(source)
            }
            it.onComplete()
        }
            .map {
                val body = doc(it.searchUrl(bookName)).body();
                if (body != null) {
                    it.fetchSearchBook(it, body)
                } else {
                    mutableListOf()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
    }

    fun getBookDetail(book: Book, callback: Consumer<BookDetail>) {
        Observable.just(book)
            .map {
                logD("详情地址" + it.bookDetailUrl)
                it.baseSource.fetchBookDetail(it, doc(it.bookDetailUrl).body())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
    }

    fun getBookrackContent(bookrack: Bookrack, callback: Consumer<String>) {
        Observable.just(bookrack)
            .map {
                it.book.baseSource.fetchContent(doc(it.url).body())
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
    }

}