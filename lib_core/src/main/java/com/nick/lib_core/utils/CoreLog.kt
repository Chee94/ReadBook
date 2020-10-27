package com.nick.lib_core.utils

import android.util.Log

/**
 *  Nick in 2020/10/27 20:09
 *  Des:
 */

var LogTAG = "小说"

val logD = { msg: String -> Log.d(LogTAG, "\n" + msg) }
val logE = { msg: String -> Log.e(LogTAG, "\n" + msg) }