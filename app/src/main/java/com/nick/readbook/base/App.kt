package com.nick.readbook.base

import android.app.Application
import com.nick.lib_core.utils.LogTAG

/**
 *  Nick in 2020/10/27 21:00
 *  Des:
 */

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        LogTAG = "小说"
    }

}