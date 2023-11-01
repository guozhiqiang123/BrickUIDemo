package com.example.brickui.tab3

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brickui.base.BaseFragment
import com.scwang.smart.refresh.header.ClassicsHeader
import com.seewo.brick.ktx.MATCH_PARENT
import com.seewo.brick.ktx.liveSimpleRecyclerView
import com.seewo.brick.ktx.smartRefresh
import com.seewo.brick.ktx.static

class Tab3Fragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return requireContext().smartRefresh(
            refreshHeader = ClassicsHeader(context)
                .setDrawableSize(12f)
                .setDrawableMarginRight(8f)
                .setTextSizeTitle(TypedValue.COMPLEX_UNIT_DIP, 12f)
                .setAccentColor(Color.parseColor("#AAAAAA"))
                .setEnableLastTime(false),
            onRefresh = { },
            loadMoreEnable = false
        ) {
            liveSimpleRecyclerView(
                MATCH_PARENT,
                MATCH_PARENT,
                data = listOf<String>().static
            ) { data, index ->

            }
        }
    }
}