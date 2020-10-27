package com.nick.readbook.ui

import android.Manifest
import com.nick.lib_core.utils.base.BaseActivity
import com.nick.readbook.R
import com.nick.readbook.adapter.BookAdapter
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var bookAdapter: BookAdapter? = null

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
            if (it){

            }
        })
    }

}