package com.nick.readbook.ui

import android.content.Context
import android.content.Intent
import android.text.Html
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_seek_book.SeekBook
import com.nick.lib_seek_book.bean.Bookrack
import com.nick.readbook.R
import com.nick.readbook.config.BOOKRACK_KEY
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_read_book.*

/**
 *  Nick in 2020/10/28 0:22
 *  Des:
 */
class ReadBookActivity : BaseActivity() {

    var bookrack: Bookrack? = null

    companion object {
        fun start(context: Context, bookrack: Bookrack) {
            val intent = Intent(context, ReadBookActivity::class.java)
            intent.putExtra(BOOKRACK_KEY, bookrack)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_read_book

    override fun init() {
        bookrack = intent.getSerializableExtra(BOOKRACK_KEY) as Bookrack?

        bookrack?.let {
            SeekBook.instance.getBookrackContent(bookrack!!, Consumer {
                tv_content.text=Html.fromHtml(it)
            })
        }

    }

}