package com.example.brickui.tab2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brickui.BrickApp
import com.example.brickui.R
import com.example.brickui.model.GameListModel
import com.example.brickui.theme.colorText
import com.example.brickui.theme.colorTextRed
import com.example.brickui.theme.colorWhite
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.seewo.brick.BrickPreview
import com.seewo.brick.BrickUI
import com.seewo.brick.ktx.MATCH_PARENT
import com.seewo.brick.ktx.WRAP_CONTENT
import com.seewo.brick.ktx.bindNotNull
import com.seewo.brick.ktx.column
import com.seewo.brick.ktx.constraintLayout
import com.seewo.brick.ktx.dividerDecoration
import com.seewo.brick.ktx.dp
import com.seewo.brick.ktx.drawable
import com.seewo.brick.ktx.frameLayout
import com.seewo.brick.ktx.imageView
import com.seewo.brick.ktx.layoutParams
import com.seewo.brick.ktx.liveRecyclerView
import com.seewo.brick.ktx.rectDrawable
import com.seewo.brick.ktx.smartRefresh
import com.seewo.brick.ktx.sp
import com.seewo.brick.ktx.static
import com.seewo.brick.ktx.textView
import com.seewo.brick.ktx.view
import com.seewo.brick.params.CornerRadius
import com.seewo.brick.params.EdgeInsets

/**
 * 这是游戏商品的Item样式
 */
@SuppressLint("ResourceType")
fun Context.GameItemUI() =
    constraintLayout(
        MATCH_PARENT,
        120.dp,
        id = 0x100,
        foreground = R.drawable.ripple_grey_2dp.drawable,
    ) {
        //封面图
        layoutParams({
            startToStart = 0x100
        }) {
            imageView(120.dp, MATCH_PARENT, id = 0x101)
        }
        //名称
        layoutParams({
            topToTop = 0x100
            startToEnd = 0x101
            endToStart = 0x105
        }) {
            textView(
                0.dp,
                WRAP_CONTENT,
                id = 0x102,
                margin = EdgeInsets.only(start = 8.dp, end = 8.dp),
                textSize = 16.sp,
                textColor = colorText,
                textStyle = Typeface.BOLD
            )
        }
        //区域
        layoutParams({
            topToBottom = 0x102
            startToStart = 0x102
        }) {
            textView(id = 0x103, margin = EdgeInsets.only(top = 4.dp))
        }

        //价格
        layoutParams({
            bottomToBottom = 0x100
            startToStart = 0x102
        }) {
            textView(id = 0x104, textColor = colorTextRed)
        }

        //点赞
        layoutParams({
            topToTop = 0x106
            endToStart = 0x106
            marginEnd = 8.dp
            topMargin = 1.dp
            goneEndMargin = 8.dp
            goneTopMargin = 9.dp
        }) {
            imageView(
                16.dp,
                16.dp,
                id = 0x105,
                drawable = R.drawable.ic_game_commend.drawable
            )
        }
        //评分
        layoutParams({
            topToTop = 0x100
            endToEnd = 0x100
        }) {
            textView(
                18.dp,
                18.dp,
                textColor = colorWhite,
                textSize = 10.sp,
                background = rectDrawable(
                    strokeColor = "#f4ce54".toColorInt(),
                    strokeWidth = 2.dp,
                    fillColor = "#323431".toColorInt(),
                    corners = CornerRadius.all(2.dp)
                ),
                id = 0x106,
                margin = EdgeInsets.only(top = 8.dp, end = 8.dp),
                gravity = Gravity.CENTER
            )
        }
        //折扣
        layoutParams({
            endToEnd = 0x100
            bottomToBottom = 0x100
        }) {
            textView(
                80.dp,
                30.dp,
                background = R.drawable.bg_game_discount.drawable,
                gravity = Gravity.CENTER,
                textColor = colorWhite,
                id = 0x107
            )
        }
        //剩余天数
        layoutParams({
            bottomToTop = 0x107
            endToEnd = 0x017
        }) {
            textView(textColor = "#999999".toColorInt(), textSize = 10.sp, id = 0x108)
        }
    }

@SuppressLint("ResourceType")
fun Context.TabListUI(
    gameData: LiveData<List<GameListModel>>,
    onLoadMore: ((RefreshLayout) -> Unit)? = null,
    onClickItem: (GameListModel, Int) -> Unit
) = smartRefresh(
    refreshEnable = false,
    refreshFooter = ClassicsFooter(this)
        .setDrawableSize(12f)
        .setDrawableMarginRight(8f)
        .setTextSizeTitle(TypedValue.COMPLEX_UNIT_DIP, 12f)
        .setAccentColor(Color.parseColor("#AAAAAA")),
    onLoadMore = onLoadMore
) {
    column(MATCH_PARENT, MATCH_PARENT) {
        liveRecyclerView(
            MATCH_PARENT,
            MATCH_PARENT,
            itemDecoration = dividerDecoration(
                orientation = LinearLayoutManager.VERTICAL,
                height = 12.dp,
            ),
            data = gameData,
            viewTypeBuilder = { 0 },
            viewBuilder = { GameItemUI() }) { data, position, view ->
            val model = data[position].gameModel
            Glide.with(context)
                .load(model.img)
                .into(view.findViewById<ImageView>(0x101))
            view.findViewById<TextView>(0x102).text = model.name
            view.findViewById<TextView>(0x103).text = model.getLabelDesc()
            view.findViewById<TextView>(0x104).text = model.getPriceDesc()
            view.findViewById<ImageView>(0x105).isVisible = model.commend != 0
            view.findViewById<TextView>(0x106).text = model.grade.toString()
            view.findViewById<TextView>(0x106).isVisible = model.grade != 0
            view.findViewById<TextView>(0X107).text = model.getDiscountDesc()
            view.findViewById<TextView>(0X108).text = model.getRemainDate()

            view.setOnClickListener {
                onClickItem(data[position], position)
            }
        }.apply {
            /**
             * 关闭动画是为了全量刷新不会闪烁，因为在GameListModel#areSameItem永远是false，则不符合正常开发规则
             */
            itemAnimator = null
        }
    }
}

class GameItemPreview(context: Context, attributeSet: AttributeSet?) :
    BrickPreview(context, attributeSet) {
    override fun preview(context: Context) {
        view { context.GameItemUI() }
    }
}

class TabListUIPreview(context: Context, attributeSet: AttributeSet?) :
    BrickPreview(context, attributeSet) {
    override fun preview(context: Context) {
        view {
            context.TabListUI(listOf<GameListModel>().static) { _, _ ->

            }
        }
    }
}