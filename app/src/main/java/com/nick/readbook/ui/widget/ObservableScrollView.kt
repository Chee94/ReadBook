package com.nick.readbook.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

/**
 *  Nick in 2020/10/29 20:22
 *  Des:
 */
class ObservableScrollView : ScrollView {

    var scrollViewListener: ScrollViewListener? = null
    var moveX: Float? = null
    var moveY: Float? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {

        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            moveX = ev!!.x
            moveY = ev!!.y
        } else if (ev!!.action == MotionEvent.ACTION_UP) {
            if (scrollViewListener != null) {
                scrollViewListener!!.onScrollChanged((moveX!! - ev!!.x), moveY!! -ev!!.y)
            }
        }

        return super.onTouchEvent(ev)
    }

}