package com.nick.lib_seek_book

import com.nick.lib_seek_book.bean.Book
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

      companion object{
          val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
              SeekBook()
          }
      }


    fun search(bookName: String,callback:Consumer<MutableList<Book>>){
        Observable.create<BaseSource> {
            for (source in SourceList){
                it.onNext(source)
            }
            it.onComplete() }
            .map {
                it. fetchBookrack( doc(it.searchUrl(bookName)).body())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
    }


}