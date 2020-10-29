package com.nick.readbook.ui

import android.Manifest
import androidx.recyclerview.widget.LinearLayoutManager
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_seek_book.BookManager
import com.nick.lib_seek_book.bean.BookDetailLocal
import com.nick.readbook.R
import com.nick.readbook.adapter.BookshelfAdapter
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var bookAdapter = BookshelfAdapter()

    override fun getLayoutId() = R.layout.activity_main

    override fun init() {
        btn_go_search.setOnClickListener {
            SearchActivity.start(MainActivity@ this)
        }
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe(Consumer {
            if (it) {

            }
        })

        bookRecyclerView.layoutManager = LinearLayoutManager(getAct())
        bookRecyclerView.adapter = bookAdapter
        bookAdapter.onAttachedToRecyclerView(bookRecyclerView)
        bookAdapter.setOnItemClickListener { adapter, view, position ->
            BookDetailsActivity.start(
                SearchActivity@ this,
                bookAdapter!!.getItem(position).lruChapters.book
            ,    bookAdapter!!.getItem(position).lruChapters
            ,    bookAdapter!!.getItem(position).position
            )
        }
    }


    override fun onResume() {
        super.onResume()
        refresh()
    }

    /**
     * 更新数据
     */
    fun refresh() {
        val keylist = BookManager.instance.bookKv.allKeys()
        keylist?.let {
            bookAdapter.setList(mutableListOf())
            for (item in keylist) {
                bookAdapter.addData(
                    BookManager.instance.bookKv.decodeParcelable(
                        item,
                        BookDetailLocal::class.java
                    )
                )
            }
        }
    }

}