package com.ystar.commonlib.utils

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.ystar.commonlib.R
import com.ystar.commonlib.interfaces.IbasePagerView
import com.ystar.ystarbaselib.utils.ClickUtils

/**
 *  滑动一页添加
 *  val snapHelper = PagerSnapHelper()
snapHelper.attachToRecyclerView(recyclerView)
 本文件只做一些常规的布局扩展，如果有特殊复杂的需求 到时候再次添加

 */

/**
 * @Author ystar
 * @Date 2021/11/29 15:33
 * @discriable
 */

open interface NodataOnclick {
    fun onclick()
}

interface AdapterOnClickItemLister {
    fun adapterOnClickItem(adapter: BaseQuickAdapter<*, *>, view: View, position: Int)
}

interface AdapterItemChildClickLister {
    fun adapterOnChildClickItem(adapter: BaseQuickAdapter<*, *>, view: View, position: Int)
}

interface AdapterLongClickLister {
    fun longItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int)
}

/**
 * 扩展函数 设置 流动性弹性布局
 * @receiver RecyclerView
 */

fun <T> RecyclerView.initflexManaage(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    recyclerView: RecyclerView,
) {
    val manager = FlexboxLayoutManager(mActivitiy)
    //设置主轴排列方式
    //设置主轴排列方式
    manager.flexDirection = FlexDirection.ROW
    //设置是否换行
    //设置是否换行
    manager.flexWrap = FlexWrap.WRAP
    manager.alignItems = AlignItems.STRETCH
    recyclerView.layoutManager = manager
    recyclerView.adapter = adapter
}

/**
 * 素质排序
 * @param mActivitiy Context
 * @param recyclerView RecyclerView
 * @param height Int
 * @param color Int
 */
private fun setLinearLayoutManagerr(
    mActivitiy: Context,
    recyclerView: RecyclerView,
    height: Int,
    color: Int,
) {
    recyclerView.layoutManager = LinearLayoutManager(mActivitiy)
    setLinearLayoutManagerr(mActivitiy, recyclerView, height, color, ItemDecorationType.VERTICAL)
}

private fun setLinearLayoutManagerr(
    mActivitiy: Context,
    recyclerView: RecyclerView,
    height: Int,
    color: Int,
    itemDecorationType: ItemDecorationType,
) {
    recyclerView.layoutManager = LinearLayoutManager(mActivitiy)
    val itemDecoration = YstarItemDecoration(color, height, itemDecorationType)
    recyclerView.addItemDecoration(itemDecoration)
    //解决ScrollView嵌套RecyclerView滑动卡帧 //设置为false   如果true 是为了和CoordinatorLayout 产生联系
    recyclerView.isNestedScrollingEnabled = true

}


/**
 * RecyclerView 一个setadapter的扩展还是  BaseQuickAdapter 结合使用
 * @receiver RecyclerView
 * @param mActivitiy Context
 * @param adapter BaseQuickAdapter<T, BaseViewHolder>
 * @param recyclerView RecyclerView
 */

fun <T> RecyclerView.initLinearLayout(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,

    ) {
    initVERTICALTotal(
        mActivitiy,
        adapter,
        this,
        1,
        R.color.common_grayf0,
        null,
        null,
        null
    )
}

fun <T> RecyclerView.initLinearLayout(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    height: Int,
) {
    initVERTICALTotal(
        mActivitiy,
        adapter,
        this,
        height,
        R.color.common_grayf0,
        null,
        null,
        null
    )
}

fun <T> RecyclerView.initLinearLayout(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    height: Int,
    empty: String,
) {
    initVERTICALTotal(
        mActivitiy,
        adapter,
        this,
        height,
        R.color.common_grayf0,
        empty,
        null,
        null
    )
}

fun <T> RecyclerView.initLinearLayout(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    height: Int,
    empty: String,
    hinttext: String,
) {
    initVERTICALTotal(
        mActivitiy,
        adapter,
        this,
        height,
        R.color.common_grayf0,
        empty,
        hinttext,
        null
    )
}

fun <T> RecyclerView.initLinearLayout(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    height: Int,
    empty: String,
    hinttext: String,
    nodataOnclick: NodataOnclick,
) {
    initVERTICALTotal(
        mActivitiy,
        adapter,
        this,
        height,
        R.color.common_grayf0,
        empty,
        hinttext,
        nodataOnclick
    )
}

fun <T> initVERTICALTotal(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    recyclerView: RecyclerView,
    height: Int,
    color: Int, empty: String?, hinttext: String?, nodataOnclick: NodataOnclick?,
) {

    setLinearLayoutManagerr(mActivitiy, recyclerView, height, color)
    val emtyView: View = LayoutInflater.from(mActivitiy).inflate(
        R.layout.common_item_emptyview,
        null
    )
    val emtpyText = emtyView.findViewById<TextView>(R.id.empty_text)

    if (!TextUtils.isEmpty(empty)) {
        emtpyText.text = empty
        adapter.setEmptyView(emtyView)
    }
    val btNodata = emtyView.findViewById<TextView>(R.id.bt_nodata_onclik)
    if (!TextUtils.isEmpty(hinttext)) {
        btNodata.visibility = View.VISIBLE
        btNodata.text = hinttext
        ClickUtils.setClick(btNodata) { nodataOnclick?.onclick() }
    } else {
        btNodata.visibility = View.GONE
    }
    recyclerView.adapter = adapter
}

//***********************************************
fun <T> RecyclerView.initGridLayout(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    msize: Int,
) {
    initHORIZONTAL(mActivitiy, adapter, this, msize, RecyclerView.VERTICAL, null)
}

fun <T> RecyclerView.initHORIZON(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    msize: Int,
    empty: String,
) {
    initHORIZONTAL(mActivitiy, adapter, this, msize, RecyclerView.VERTICAL, empty)
}


private fun <T> initHORIZONTAL(
    mActivitiy: Context,
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    recyclerView: RecyclerView,
    msize: Int,
    @RecyclerView.Orientation orientation: Int,
    empty: String?,
) {
    recyclerView.layoutManager = GridLayoutManager(mActivitiy, msize, orientation, false)
// 将SnapHelper attach 到RecyclrView

    // 将SnapHelper attach 到RecyclrView
    recyclerView.isNestedScrollingEnabled = true
    empty?.let {
        val emtyview = LayoutInflater.from(mActivitiy).inflate(R.layout.common_item_emptyview, null)
        val emtpytext = emtyview.findViewById<TextView>(R.id.empty_text)
        emtpytext.text = empty
        adapter.setEmptyView(emtyview)
    }

    recyclerView.adapter = adapter
}


//**********************刷新下拉*******************************************
    fun <T>  SmartRefreshLayout.setRefresh(ibasePagerView: IbasePagerView){
    this.setOnRefreshListener {
        ibasePagerView.refresh()
    }
    this.setOnLoadMoreListener {
        ibasePagerView.loadMore()

    }
}

/**
 * 刷新成功
 * @receiver SmartRefreshLayout
 * @param isfirstpage Boolean
 */
fun SmartRefreshLayout.refreshSuc(isFirstPage: Boolean) {
    if (isFirstPage) { //首次加载
        if (this.isRefreshing) this.finishRefresh()
    } else { //加载更多
        if (this.isLoading) this.finishLoadMore()
    }
    this.setEnableLoadMore(true)
}


/**
 * 刷新成功 且不需要加载更多功能
 * @receiver SmartRefreshLayout
 * @param isfirstpage Boolean
 */
fun SmartRefreshLayout.refreshSucWithNoLoad(isFirstPage: Boolean) {
    if (isFirstPage) { //首次加载
        if (this.isRefreshing) this.finishRefresh()
    } else { //加载更多
        if (this.isLoading) this.finishLoadMore()
    }
}

/**
 * @param smartRefreshLayout
 * @param isfirstpage        是否是第一页
 * @param       // 是第一页，全屏显示无数据状态  禁止刷新和加载
 */
fun SmartRefreshLayout.end( isFirstPage: Boolean) {
    if (isFirstPage) {
        this.finishRefresh()
    } else {
        this.finishLoadMore()
    }
    this.setEnableLoadMore(false)
}
