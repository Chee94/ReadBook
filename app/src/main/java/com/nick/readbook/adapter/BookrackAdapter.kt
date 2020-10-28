package com.nick.readbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.nick.lib_seek_book.bean.Chapters
import com.nick.readbook.R

/**
 *  Nick in 2020/10/27 23:29
 *  Des:
 */
class BookrackAdapter : BaseQuickAdapter<Chapters, BaseViewHolder>(R.layout.item_bookrack) {

    override fun convert(holder: BaseViewHolder, item: Chapters) {
        holder.setText(R.id.tv_bookrack, item.name)
    }

}