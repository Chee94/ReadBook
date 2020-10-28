package com.nick.readbook.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.nick.lib_seek_book.bean.BookDetailLocal
import com.nick.readbook.R

/**
 *  Nick in 2020/10/28 21:57
 *  Des:
 */
class BookshelfAdapter :
    BaseQuickAdapter<BookDetailLocal, BaseViewHolder>(R.layout.item_bookshelf) {

    override fun convert(holder: BaseViewHolder, item: BookDetailLocal) {
        Glide.with(context).load(item.book.imgUrl).into(holder.getView(R.id.iv_img))
        holder.setText(R.id.tv_book_name, item.lruChapters.book.name)
        holder.setText(R.id.tv_author, item.lruChapters.book.author)
        holder.setText(R.id.tv_news, item.lruChapters.book.newChapters)
    }

}