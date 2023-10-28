package com.example.brickui.tab1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.CHAIN_PACKED
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brickui.MainActivity
import com.example.brickui.R
import com.example.brickui.ext.toast
import com.example.brickui.model.MainMenuModel
import com.example.brickui.theme.colorBgGradientEnd
import com.example.brickui.theme.colorBgGradientStart
import com.example.brickui.theme.colorBgStorageSize
import com.example.brickui.theme.colorText
import com.example.brickui.theme.colorTextGrey
import com.example.brickui.theme.colorWhite
import com.example.brickui.theme.colorWindow
import com.file.everything.CircleProgress
import com.seewo.brick.BrickPreview
import com.seewo.brick.ktx.MATCH_PARENT
import com.seewo.brick.ktx.WRAP_CONTENT
import com.seewo.brick.ktx.column
import com.seewo.brick.ktx.constraintLayout
import com.seewo.brick.ktx.dp
import com.seewo.brick.ktx.drawable
import com.seewo.brick.ktx.expand
import com.seewo.brick.ktx.frameLayout
import com.seewo.brick.ktx.getString
import com.seewo.brick.ktx.gridSpaceDecoration
import com.seewo.brick.ktx.imageView
import com.seewo.brick.ktx.layoutParams
import com.seewo.brick.ktx.rectDrawable
import com.seewo.brick.ktx.row
import com.seewo.brick.ktx.shapeDrawable
import com.seewo.brick.ktx.simpleRecyclerView
import com.seewo.brick.ktx.sp
import com.seewo.brick.ktx.string
import com.seewo.brick.ktx.textView
import com.seewo.brick.ktx.view
import com.seewo.brick.ktx.withAlpha
import com.seewo.brick.params.CornerRadius
import com.seewo.brick.params.EdgeInsets
import com.seewo.brick.view.parentId


@SuppressLint("ResourceType")
fun Context.tab1FragmentUI(): View = column(
    MATCH_PARENT,
    MATCH_PARENT,
    background = rectDrawable(fillColor = colorWindow)
) {
    column(
        MATCH_PARENT,
        WRAP_CONTENT,
        background = shapeDrawable(
            colors = intArrayOf(
                colorBgGradientStart,
                colorBgGradientEnd
            ),
            corners = CornerRadius.only(leftBottom = 40.dp, rightBottom = 40.dp)
        )
    ) {
        row(MATCH_PARENT, 56.dp) {
            textView(
                text = "Hello,",
                textSize = 18.sp,
                textColor = colorWhite,
                margin = EdgeInsets.only(start = 28.dp)
            )
            expand()
            imageView(
                28.dp,
                28.dp,
                drawable = R.drawable.ic_main_menu.drawable,
                margin = EdgeInsets.only(end = 20.dp)
            ) {
                if (this@tab1FragmentUI is MainActivity) {
                    this@tab1FragmentUI.showDrawer()
                }
            }
        }
        textView(
            text = R.string.welcome_to_us.getString(R.string.app_name.string),
            textSize = 30.sp,
            textColor = colorWhite,
            padding = EdgeInsets.symmetric(horizontal = 28.dp)
        )
        constraintLayout(
            MATCH_PARENT,
            150.dp,
            id = 0x123,
            background = rectDrawable(
                corners = CornerRadius.all(28.dp),
                fillColor = colorBgStorageSize
            ),
            margin = EdgeInsets.only(start = 20.dp, top = 32.dp, end = 20.dp, bottom = 28.dp)
        ) {
            layoutParams({
                startToStart = parentId
                topToTop = parentId
            }) {
                textView(
                    id = 0x124,
                    text = "80GB / 160GB Used",
                    textColor = colorWhite,
                    textSize = 16.sp,
                    margin = EdgeInsets.only(start = 20.dp, top = 29.dp)
                )
            }
            layoutParams({
                topToBottom = 0x124
                startToStart = 0x124
            }) {
                textView(
                    text = "80 GB Available",
                    textColor = colorWhite.withAlpha(0.4f),
                    textSize = 14.sp
                )
            }
            layoutParams({
                startToStart = 0x124
                bottomToBottom = 0x123
            }) {
                row(
                    164.dp,
                    40.dp,
                    foreground = R.drawable.ripple_grey_12dp.drawable,
                    background = rectDrawable(
                        fillColor = colorWhite,
                        corners = CornerRadius.all(12.dp)
                    ),
                    margin = EdgeInsets.only(bottom = 25.dp),
                    padding = EdgeInsets.symmetric(horizontal = 12.dp),
                    onClick = {

                    }
                ) {
                    textView(
                        text = "Internal Storage",
                        textColor = "#1D3D7A".toColorInt(),
                        textSize = 14.sp,
                        textStyle = Typeface.BOLD
                    )
                    expand()
                    imageView(
                        drawable = R.drawable.ic_main_blue_arrow.drawable,
                    )
                }
            }

            addView(
                CircleProgress(context, null).apply {
                    id = 0x125
                    layoutParams = ConstraintLayout.LayoutParams(105.dp, 105.dp).apply {
                        topToTop = parentId
                        bottomToBottom = parentId
                        endToEnd = paddingEnd
                        marginEnd = 16.dp
                    }
                    maxValue = 100f
                    value = 30f
                    gradientColors = intArrayOf(colorWhite, colorWhite)
                    setArcWidth(15.dp)
                    setBgArcWidth(15.dp)
                    setBgArcColor(colorWhite.withAlpha(0.3f))
                }
            )

            layoutParams({
                topToTop = 0x125
                startToStart = 0x125
                endToEnd = 0x125
            }) {
                row(margin = EdgeInsets.only(top = 32.5.dp), id = 0x126) {
                    textView(
                        text = "30",
                        textColor = colorWhite,
                        textSize = 18.sp,
                        textStyle = Typeface.BOLD
                    )
                    textView(text = "%", textColor = colorWhite, textSize = 10.sp)
                }
            }

            layoutParams({
                topToBottom = 0x126
                startToStart = 0x126
                endToEnd = 0x126
            }) {
                textView(
                    text = "used",
                    textSize = 10.sp,
                    textColor = colorWhite
                )
            }
        }
    }
    expand {
        simpleRecyclerView(
            MATCH_PARENT,
            WRAP_CONTENT,
            data = listOf(
                MainMenuModel("Photos", R.drawable.menu_photo, "--"),
                MainMenuModel("Videos", R.drawable.menu_video, "--"),
                MainMenuModel("Audios", R.drawable.menu_audio, "--"),
                MainMenuModel("Docs", R.drawable.menu_doc, "--"),
                MainMenuModel("Zip", R.drawable.menu_zip, "--"),
                MainMenuModel("Apk", R.drawable.menu_apk, "--"),
            ),
            layoutManager = GridLayoutManager(context, 2),
            padding = EdgeInsets.symmetric(horizontal = 10.dp, vertical = 16.dp),
            itemDecoration = gridSpaceDecoration(10.dp, 10.dp)
        ) { data, i ->
            val itemModel = data[i]
            mainMenu(itemModel) { toast("点击了${itemModel.name}") }
        }.apply {
            overScrollMode = View.OVER_SCROLL_NEVER
            clipToPadding = false
        }
    }
}

/**
 * 把可能会复用的组件抽离。请遵循compose的状态提升思想，把事件的处理交给调用者，这样更有利于复用
 */
@SuppressLint("ResourceType")
fun ViewGroup.mainMenu(itemModel: MainMenuModel, onClick: View.OnClickListener) = constraintLayout(
    MATCH_PARENT,
    90.dp,
    id = 0x130,
    background = rectDrawable(
        fillColor = colorWhite,
        corners = CornerRadius.all(10.dp)
    ),
    foreground = R.drawable.ripple_grey_12dp.drawable,
    onClick = onClick
) {
    layoutParams({
        topToTop = 0x130
        bottomToBottom = 0x130
        startToStart = 0x130
    }) {
        imageView(
            40.dp,
            40.dp,
            id = 0x131,
            drawable = itemModel.icon.drawable,
            margin = EdgeInsets.only(start = 16.dp)
        )
    }

    layoutParams({
        topToTop = 0x130
        bottomToTop = 0x133
        startToEnd = 0x131
        verticalChainStyle = CHAIN_PACKED
    }) {
        textView(
            id = 0x132,
            text = itemModel.name,
            textColor = colorText,
            textSize = 14.dp,
            textStyle = Typeface.BOLD,
            margin = EdgeInsets.only(start = 12.dp)
        )
    }

    layoutParams({
        topToBottom = 0x132
        bottomToBottom = 0x130
        startToStart = 0x132
    }) {
        textView(
            id = 0x133,
            text = itemModel.size,
            textColor = colorTextGrey,
            textSize = 12.dp
        )
    }
}


/**
 * 整个页面的预览
 */
class MainUIPreview(context: Context, attributeSet: AttributeSet?) :
    BrickPreview(context, attributeSet) {
    override fun preview(context: Context) {
        view {
            context.tab1FragmentUI()
        }
    }
}

/**
 * 功能菜单的预览
 */
class MainMenuPreview(context: Context, attributeSet: AttributeSet?) :
    BrickPreview(context, attributeSet) {
    override fun preview(context: Context) {
        frameLayout(MATCH_PARENT, MATCH_PARENT) {
            layoutParams(gravity = Gravity.CENTER) {
                mainMenu(MainMenuModel("预览", R.mipmap.ic_launcher, "--")) {
                }
            }
        }
    }
}