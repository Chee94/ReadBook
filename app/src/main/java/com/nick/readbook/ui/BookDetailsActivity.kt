package com.nick.readbook.ui

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_core.utils.logD
import com.nick.lib_seek_book.BookManager
import com.nick.lib_seek_book.SeekBook
import com.nick.lib_seek_book.bean.Book
import com.nick.readbook.R
import com.nick.readbook.adapter.BookrackAdapter
import com.nick.readbook.config.BOOK_KEY
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_book_details.*

/**
 *  Nick in 2020/10/27 22:57
 *  Des: 书本详情页面
 */
class BookDetailsActivity : BaseActivity() {

    var book: Book? = null
    var adapter: BookrackAdapter? = null

    companion object {
        fun start(context: Context, book: Book) {
            val intent = Intent(context, BookDetailsActivity::class.java);
            intent.putExtra(BOOK_KEY, book)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_book_details

    override fun init() {
        adapter = BookrackAdapter();

        adapter?.let {
            bookrackRecyclerView.adapter = adapter;
            bookrackRecyclerView.layoutManager = LinearLayoutManager(getAct())
            adapter!!.onAttachedToRecyclerView(bookrackRecyclerView)
            adapter!!.setOnItemClickListener { a, view, position ->
                BookManager.instance.currentBookrack = position
                ReadBookActivity.start(
                    getAct()
                )
            }
        }

        book = intent.getSerializableExtra(BOOK_KEY) as Book?
        book?.let {
            SeekBook.instance.getBookDetail(book!!, Consumer {
                tv_title.text = it.book.name
                tv_author.text = it.book.author
                tv_update.text = it.book.updateDate
                logD("图片地址" + it.imgUrl)
                Glide.with(getAct()).load(it.imgUrl).into(iv_img)
                adapter!!.setList(it.bookrackList)
                BookManager.instance.setBookrackList(it.bookrackList)
            })
        }


    }

}