package com.nick.lib_core.utils.base

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 *  Nick in 2020/10/28 21:40
 *  Des:
 */
abstract class BaseApp :Application(){

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this);
    }

}