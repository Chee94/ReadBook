package com.nick.lib_core.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *  Nick in 2020/10/27 20:42
 *  Des:
 */

open abstract class BaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    abstract fun getLayoutId():Int

    abstract fun init()

}