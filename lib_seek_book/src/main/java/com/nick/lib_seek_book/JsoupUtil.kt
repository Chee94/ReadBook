package com.nick.lib_seek_book

import org.jsoup.Jsoup

/**
 *  Nick in 2020/10/26 23:40
 *  Des:
 */

val  doc = {url:String->Jsoup.connect(url).get()}