package com.example.brickui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.example.brickui.tab1.Tab1Fragment
import com.example.brickui.tab2.Tab2Fragment
import com.example.brickui.tab3.Tab3Fragment
import com.example.brickui.theme.colorDivider
import com.example.brickui.theme.colorTabNormal
import com.example.brickui.theme.colorTheme
import com.seewo.brick.BrickPreview
import com.seewo.brick.ktx.MATCH_PARENT
import com.seewo.brick.ktx.column
import com.seewo.brick.ktx.divider
import com.seewo.brick.ktx.dp
import com.seewo.brick.ktx.drawable
import com.seewo.brick.ktx.expand
import com.seewo.brick.ktx.live
import com.seewo.brick.ktx.liveFragmentPager
import com.seewo.brick.ktx.liveTabLayout
import com.seewo.brick.ktx.sp
import com.seewo.brick.ktx.stateListColor
import com.seewo.brick.ktx.static
import com.seewo.brick.ktx.textView
import com.seewo.brick.ktx.view
import com.seewo.brick.params.CompoundDrawables
import com.seewo.brick.params.EdgeInsets


private fun Context.mainPageUI() = column(
    MATCH_PARENT, MATCH_PARENT,
    fitsSystemWindows = true
) {
    val currentIndex = 0.live
    val tabs = listOf(
        Pair("Tab1", R.drawable.selector_tab_home),
        Pair("Tab2", R.drawable.selector_tab_storage),
        Pair("Tab3", R.drawable.selector_tab_setting)
    )
    expand {
        liveFragmentPager(
            MATCH_PARENT, MATCH_PARENT,
            data = tabs.static,
            currentIndex = currentIndex,
            isUserInputEnable = false
        ) { _, index ->
            when (index) {
                0 -> Tab1Fragment()
                1 -> Tab2Fragment()
                2 -> Tab3Fragment()
                else -> throw Exception("错误代码")
            }
        }
    }
    divider(MATCH_PARENT, 1.dp, background = ColorDrawable(colorDivider))
    liveTabLayout(
        MATCH_PARENT, 64.dp,
        data = tabs,
        currentIndex = currentIndex,
        onTabSelected = {

        }
    ) { _, item ->
        textView(
            text = item.first,
            textColorList = stateListColor(
                mapOf(
                    intArrayOf(-android.R.attr.state_selected) to colorTabNormal,
                    intArrayOf(android.R.attr.state_selected) to colorTheme
                )
            ),
            textSize = 12.sp,
            compoundDrawables = CompoundDrawables(top = item.second.drawable),
            drawablePadding = 2.dp,
            padding = EdgeInsets.symmetric(vertical = 5.dp)
        )
    }

}

fun MainActivity.contentView(): View = mainPageUI()

class MainPageUIPreview(context: Context, attributeSet: AttributeSet?) :
    BrickPreview(context, attributeSet) {
    override fun preview(context: Context) {
        view { context.mainPageUI() }
    }
}