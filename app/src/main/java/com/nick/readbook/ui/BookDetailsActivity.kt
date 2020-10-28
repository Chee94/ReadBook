package com.nick.readbook.ui

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_core.utils.logD
import com.nick.lib_seek_book.BookManager
import com.nick.lib_seek_book.SeekBook
import com.nick.lib_seek_book.bean.Book
import com.nick.lib_seek_book.bean.Chapters
import com.nick.readbook.R
import com.nick.readbook.adapter.BookrackAdapter
import com.nick.readbook.config.BOOK_KEY
import com.nick.readbook.config.LRU_CHAPTERS_KEY
import com.nick.readbook.config.LRU_POSITION_KEY
import com.nick.readbook.util.glide_options
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
        fun start(context: Context, book: Book, lruChapters: Chapters?, position: Int) {
            val intent = Intent(context, BookDetailsActivity::class.java);
            intent.putExtra(BOOK_KEY, book)
            intent.putExtra(LRU_CHAPTERS_KEY, lruChapters)
            intent.putExtra(LRU_POSITION_KEY, position)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_book_details

    override fun init() {
        book = intent.getParcelableExtra(BOOK_KEY) as Book?

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


        book?.let {
            SeekBook.instance.getBookDetail(book!!, Consumer {
                tv_title.text = it.book.name
                tv_author.text = it.book.author
                tv_update.text = it.book.updateDate
                logD("图片地址" + it.imgUrl)
                Glide.with(getAct()).load(it.imgUrl).apply(glide_options).into(iv_img)
                adapter!!.setList(it.chaptersList)
                BookManager.instance.lruPosition(intent.getIntExtra(LRU_POSITION_KEY, 0))
                    .setBookrackList(it, getAct())

                intent.getParcelableExtra<Chapters>(LRU_CHAPTERS_KEY).apply {
                    btn_lruchapters.visibility = if (this != null) View.VISIBLE else View.GONE
                    this?.let {
                        btn_lruchapters.text = "最近阅读:${this.name}"
                        ReadBookActivity.start(
                            getAct()
                        )
                    }
                }

            })

        }

        btn_back.setOnClickListener { finish() }

        btn_add.visibility =
            if (intent.getIntExtra(LRU_POSITION_KEY, 0) != 0) View.GONE else View.VISIBLE

        btn_add.setOnClickListener {
            BookManager.instance.addBookshelf { it ->
                Toast.makeText(getAct(), if (it) "添加成功" else "添加失败", Toast.LENGTH_SHORT).show()
                btn_add.visibility = if (it) View.GONE else View.VISIBLE
                0
            }
        }

    }

}