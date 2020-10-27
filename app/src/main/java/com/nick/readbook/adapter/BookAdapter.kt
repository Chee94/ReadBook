package com.nick.readbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.nick.lib_seek_book.bean.Book
import com.nick.readbook.R

/**
 *  Nick in 2020/10/27 20:37
 *  Des:
 */
class BookAdapter:BaseQuickAdapter<Book,BaseViewHolder> (R.layout.item_book){

    override fun convert(holder: BaseViewHolder, item: Book) {
        holder.setText(R.id.tv_name,item.name)
        holder.setText(R.id.tv_author,item.author)
        holder.setText(R.id.tv_date,item.updateDate)
        holder.setText(R.id.tv_new_chapters,item.newChapters)
    }

}