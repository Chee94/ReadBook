package com.nick.readbook

import androidx.recyclerview.widget.LinearLayoutManager
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_seek_book.SeekBook
import com.nick.readbook.adapter.BookAdapter
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var bookAdapter :BookAdapter?=null

    override fun getLayoutId() = R.layout.activity_main

    override fun init() {
        bookAdapter= BookAdapter();
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.adapter=bookAdapter;
        bookAdapter!!.onAttachedToRecyclerView(bookRecyclerView)

        btn.setOnClickListener {
            SeekBook.instance.search(et.text.toString(), Consumer {
                bookAdapter!!.setList(it)
            })
        }
    }

}