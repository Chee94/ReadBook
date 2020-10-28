package com.nick.readbook.util


import com.bumptech.glide.request.RequestOptions
import com.nick.readbook.R


/**
 *  Nick in 2020/10/28 19:53
 *  Des:工具集
 */

val glide_options = RequestOptions()
    .placeholder(R.drawable.ic_default)
    .fallback(R.drawable.ic_default)
    .error(R.drawable.ic_default)



