package com.example.brickui.tab2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brickui.R
import com.example.brickui.banner.HomeBannerAdapter
import com.example.brickui.ext.toast
import com.example.brickui.model.GameTabModel
import com.example.brickui.model.HomeModel
import com.example.brickui.theme.colorText
import com.example.brickui.theme.colorTextGrey
import com.example.brickui.theme.colorTextRed
import com.google.android.material.appbar.AppBarLayout
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.seewo.brick.ktx.MATCH_PARENT
import com.seewo.brick.ktx.WRAP_CONTENT
import com.seewo.brick.ktx.appBarLayout
import com.seewo.brick.ktx.bindNotNull
import com.seewo.brick.ktx.column
import com.seewo.brick.ktx.constraintLayout
import com.seewo.brick.ktx.coordinatorLayout
import com.seewo.brick.ktx.data
import com.seewo.brick.ktx.dp
import com.seewo.brick.ktx.drawable
import com.seewo.brick.ktx.expand
import com.seewo.brick.ktx.frameLayout
import com.seewo.brick.ktx.glideImage
import com.seewo.brick.ktx.gridSpaceDecoration
import com.seewo.brick.ktx.imageView
import com.seewo.brick.ktx.init
import com.seewo.brick.ktx.layoutParams
import com.seewo.brick.ktx.liveFragmentPager
import com.seewo.brick.ktx.liveSimpleRecyclerView
import com.seewo.brick.ktx.ovalClip
import com.seewo.brick.ktx.rectDrawable
import com.seewo.brick.ktx.roundRectClip
import com.seewo.brick.ktx.row
import com.seewo.brick.ktx.scrollingView
import com.seewo.brick.ktx.smartRefresh
import com.seewo.brick.ktx.sp
import com.seewo.brick.ktx.stateListColor
import com.seewo.brick.ktx.textView
import com.seewo.brick.params.CornerRadius
import com.seewo.brick.params.EdgeInsets
import com.youth.banner.Banner
import com.youth.banner.indicator.RoundLinesIndicator

@SuppressLint("ResourceType")
fun Context.tab2FragmentUI(
    bannerData: LiveData<List<HomeModel.Banner>>,
    exploreData: LiveData<List<HomeModel.Explore>>,
    eventData: LiveData<HomeModel.Event>,
    tabsData: LiveData<List<GameTabModel>>,
    currentTabIndex: LiveData<Int>,
    onRefresh: (RefreshLayout) -> Unit,
    onClickBanner: ((HomeModel.Banner, Int) -> Unit)? = null,
    onTabPageChanged: ((GameTabModel) -> Unit)? = null,
) = frameLayout(MATCH_PARENT, MATCH_PARENT) {
    smartRefresh(
        refreshHeader = ClassicsHeader(context)
            .setDrawableSize(12f)
            .setDrawableMarginRight(8f)
            .setTextSizeTitle(TypedValue.COMPLEX_UNIT_DIP, 12f)
            .setAccentColor(Color.parseColor("#AAAAAA"))
            .setEnableLastTime(false),
        onRefresh = onRefresh,
        loadMoreEnable = false
    ) {

        coordinatorLayout {
            appBarLayout {
                layoutParams(scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL) {
                    column(MATCH_PARENT, MATCH_PARENT) {

                        //Banner广告
                        constraintLayout(MATCH_PARENT, WRAP_CONTENT, id = 0x100) {

                            layoutParams({
                                topToTop = 0x1100
                                startToStart = 0x100
                                endToEnd = 0x100
                                dimensionRatio = "h, 2:1"
                            }) {
                                bannerView(id = 0x101, height = 0.dp, onClick = onClickBanner) {
                                    bannerData.bindNotNull(context) { setDatas(it) }
                                }
                            }
                        }

                        //探索标题
                        row(
                            MATCH_PARENT,
                            50.dp,
                            padding = EdgeInsets.only(start = 16.dp)
                        ) {
                            textView(
                                text = "探索",
                                textColor = colorText,
                                textStyle = Typeface.BOLD,
                                textSize = 20.sp
                            )
                            expand()
                            textView(
                                60.dp,
                                MATCH_PARENT,
                                text = "更多",
                                textSize = 14.sp,
                                textStyle = Typeface.BOLD,
                                textColor = colorTextRed,
                                foreground = R.drawable.ripple_grey_2dp.drawable,
                                gravity = Gravity.CENTER
                            ) {
                                toast("点击了更多")
                            }
                        }

                        //探索
                        liveSimpleRecyclerView(
                            MATCH_PARENT,
                            WRAP_CONTENT,
                            layoutManager = GridLayoutManager(context, 4),
                            data = exploreData,
                            itemDecoration = gridSpaceDecoration(12.dp, 12.dp),
                            padding = EdgeInsets.symmetric(horizontal = 16.dp)
                        ) { data, index ->

                            ovalClip {
                                glideImage(urlOrPath = data[index].image) {
                                    imageView(MATCH_PARENT, WRAP_CONTENT)
                                }
                            }
                        }

                        //海报
                        roundRectClip(radius = 8.dp) {
                            imageView(
                                MATCH_PARENT,
                                WRAP_CONTENT,
                                margin = EdgeInsets.all(16.dp)
                            ).apply {
                                eventData.bindNotNull(context) {
                                    Glide.with(context)
                                        .load(it.image)
                                        .into(this)
                                }
                            }
                        }

                        //最新折扣标题
                        textView(
                            MATCH_PARENT,
                            50.dp,
                            text = "最新折扣",
                            textSize = 20.sp,
                            textStyle = Typeface.BOLD,
                            textColor = colorText,
                            padding = EdgeInsets.symmetric(horizontal = 16.dp),
                            gravity = Gravity.CENTER_VERTICAL
                        )
                    }
                }

                layoutParams(scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL) {
                    val tabColorState = stateListColor(
                        mapOf(
                            intArrayOf(-android.R.attr.state_selected) to colorTextGrey,
                            intArrayOf(android.R.attr.state_selected) to colorText
                        )
                    )
                    liveSimpleRecyclerView(
                        MATCH_PARENT, 50.dp,
                        data = tabsData,
                        layoutManager = LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        ),
                        viewHolderCreator = {
                            frameLayout { }
                        }
                    ) { data, index ->
                        val itemModel = data[index]
                        column(
                            gravity = Gravity.CENTER,
                            padding = EdgeInsets.symmetric(
                                horizontal = 16.dp,
                                vertical = 4.dp
                            ),
                            onClick = {
                                onTabPageChanged?.invoke(itemModel)
                            }
                        ) {
                            textView(
                                text = itemModel.tab.title,
                                textColorList = tabColorState,
                                textSize = 18.sp,
                                textStyle = Typeface.BOLD
                            ).apply {
                                isSelected = itemModel.isSelect
                            }
                            textView(
                                text = itemModel.tab.count.toString(),
                                textColorList = tabColorState,
                                textSize = 12.sp,
                                padding = EdgeInsets.symmetric(horizontal = 6.dp),
                                background = rectDrawable(
                                    strokeColor = if (itemModel.isSelect) colorText else colorTextGrey,
                                    strokeWidth = 1.dp,
                                    corners = CornerRadius.all(20.dp)
                                )
                            ).apply {
                                isSelected = itemModel.isSelect
                            }
                        }
                    }
                }
            }

            scrollingView {
                liveFragmentPager(
                    MATCH_PARENT,
                    MATCH_PARENT,
                    data = tabsData,
                    currentIndex = currentTabIndex,
                    onPageSelected = { onTabPageChanged?.invoke(tabsData.data[it]) }
                ) { data, index ->
                    TabListFragment().apply {
                        arguments = bundleOf(TabListFragment.ARG_DATA to data[index])
                    }
                }
            }
        }
    }.apply {
        autoRefresh(0)
    }


}

/**
 * Banner
 */
fun ViewGroup.bannerView(
    width: Int = MATCH_PARENT,
    height: Int = WRAP_CONTENT,
    @IdRes id: Int? = null,
    onClick: ((HomeModel.Banner, Int) -> Unit)? = null,
    block: (Banner<HomeModel.Banner, HomeBannerAdapter>.() -> Unit)? = null
) = Banner<HomeModel.Banner, HomeBannerAdapter>(context).apply {
    init(width, height)
    id?.let { this.id = it }
    setAdapter(HomeBannerAdapter())
    indicator = RoundLinesIndicator(context)
    setOnBannerListener(onClick)
    setIntercept(false)
}.also {
    addView(it)
    block?.invoke(it)
}