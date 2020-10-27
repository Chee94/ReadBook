package com.nick.readbook.ui

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_seek_book.SeekBook
import com.nick.readbook.R
import com.nick.readbook.adapter.BookAdapter
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.bookRecyclerView
import kotlinx.android.synthetic.main.activity_search.*

/**
 *  Nick in 2020/10/27 21:46
 *  Des:
 */
class SearchActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    var bookAdapter: BookAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun init() {
        bookAdapter = BookAdapter();

        btn_search.setOnClickListener {
            SeekBook.instance.searchBook(et_search.text.toString(), Consumer {
                bookAdapter!!.setList(it)
            })
        }

        bookAdapter?.let {
            bookRecyclerView.layoutManager = LinearLayoutManager(this)
            bookRecyclerView.adapter = bookAdapter;
            bookAdapter!!.onAttachedToRecyclerView(bookRecyclerView)
            bookAdapter!!.setOnItemClickListener { adapter, view, position ->
                BookDetailsActivity.start(
                    SearchActivity@ this,
                    bookAdapter!!.getItem(position)
                )
            }
        }
    }

}