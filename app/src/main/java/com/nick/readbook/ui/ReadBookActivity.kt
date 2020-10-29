package com.nick.readbook.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.lib_seek_book.BookManager
import com.nick.readbook.R
import com.nick.readbook.ui.widget.ScrollViewListener
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun init() {

        scrollable.scrollViewListener = object : ScrollViewListener {
            override fun onScrollChanged(x: Float, y: Float) {
                if (x < -200) {
                    last()
                } else if (x > 200) {
                    next()
                }
            }
        }

        current()

        btn_next.setOnClickListener {
            next()
        }
    }

    fun next() {
        BookManager.instance.next(Consumer {
            scrollable.scrollTo(0, 0)
            tv_title.text = "${it.name}"
            tv_content.text = Html.fromHtml(it.content)
        }, getAct())
    }

    fun last(){
        BookManager.instance.last(Consumer {
            scrollable.scrollTo(0, 0)
            tv_title.text = "${it.name}"
            tv_content.text = Html.fromHtml(it.content)
        }, getAct())
    }

    fun current(){
        BookManager.instance.current(Consumer {
            scrollable.scrollTo(0, 0)
            tv_title.text = "${it.name}"
            tv_content.text = Html.fromHtml(it.content)
        }, getAct())
    }

}