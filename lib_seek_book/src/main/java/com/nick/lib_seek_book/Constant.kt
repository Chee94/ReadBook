package com.nick.lib_seek_book

import com.nick.lib_seek_book.source.BaseSource
import com.nick.lib_seek_book.source.BiQuGeSource

/**
 *  Nick in 2020/10/26 23:11
 *  Des:
 */

//所有书源
val SourceList = mutableListOf<BaseSource>(BiQuGeSource())