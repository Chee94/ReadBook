package com.nick.readbook.base

import com.nick.lib_core.utils.LogTAG
import com.nick.lib_core.utils.base.BaseApp

/**
 *  Nick in 2020/10/27 21:00
 *  Des:
 */

class App :BaseApp(){

    override fun onCreate() {
        super.onCreate()
        LogTAG = "小说"
    }

}