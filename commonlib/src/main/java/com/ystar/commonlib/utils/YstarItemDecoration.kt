package com.ystar.commonlib.utils


import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.ystar.commonlib.R


enum class ItemDecorationType {
    HORIZONTAL,
    VERTICAL


}


/**
 * @Author ystar
 * @Date 2021/11/30 9:03
 * @discriable
 */
class YstarItemDecoration() : RecyclerView.ItemDecoration() {

    private var dividerPaint: Paint = Paint()

    //默认间距 1dp
    private var dividerHeight: Int = ConvertUtils.dp2px(1f)

    //默认颜色
    var mcolor: Int = R.color.common_grayf0

    var itemDecorationType: ItemDecorationType = ItemDecorationType.VERTICAL


    constructor(dividerHeight: Int) : this(R.color.common_grayf0, dividerHeight)

    constructor(mColor: Int, dividerHeight: Int) : this(mColor,
        dividerHeight,
        ItemDecorationType.VERTICAL)

    constructor(itemDecorationType: ItemDecorationType) : this(R.color.common_grayf0,
        ConvertUtils.dp2px(1f),
        itemDecorationType)


    constructor(mColor: Int, dividerHeight: Int, itemDecorationType: ItemDecorationType) : this() {
        this.mcolor = mColor
        this.dividerHeight = dividerHeight
        this.itemDecorationType = itemDecorationType


    }


    init {
        dividerPaint.color = this.mcolor
        dividerPaint.isAntiAlias = true
    }

/*
        init {
            dividerPaint = Paint()
            dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent))
            dividerHeight = wid;
        }*/

    //设置每个item的padding
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (itemDecorationType) {
            ItemDecorationType.VERTICAL ->   outRect.set(0, 0, 0,dividerHeight )
            ItemDecorationType.HORIZONTAL -> {
            }
        }


        //   outRect.bottom = ConvertUtils.dp2px(100f)//类似加了一个bottom padding
    }

    //绘制背景
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)


    }

    //绘制item上面
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        when (itemDecorationType) {
            ItemDecorationType.VERTICAL -> drawline(c, parent, state)
            ItemDecorationType.HORIZONTAL -> {drawhline(c, parent, state)
            }
        }
    }


    private fun drawline(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        for (i in 0 until childCount - 1) {
            val view = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = view.bottom + dividerHeight.toFloat()
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint)

        }
    }


    private fun  drawhline(c: Canvas, parent: RecyclerView, state: RecyclerView.State){

    }

}