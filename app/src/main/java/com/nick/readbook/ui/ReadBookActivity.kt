package com.nick.readbook.ui

import android.content.Context
import android.content.Intent
import android.text.Html
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_seek_book.BookManager
import com.nick.readbook.R
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_read_book.*

/**
 *  Nick in 2020/10/28 0:22
 *  Des:
 */
class ReadBookActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ReadBookActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_read_book

    override fun init() {

        next()

        btn_next.setOnClickListener {
            next()
        }
    }

    fun next() {
        BookManager.instance.next(Consumer {
            scrollable.scrollTo(0, 0)
            tv_title.text="${it.name}"
            tv_content.text = Html.fromHtml(it.content)
        },getAct())
    }

}