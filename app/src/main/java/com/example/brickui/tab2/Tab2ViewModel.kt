package com.example.brickui.tab2

import androidx.lifecycle.scopeNetLife
import com.drake.net.Get
import com.example.brickui.base.BaseViewModel
import com.example.brickui.http.mock.Api
import com.example.brickui.model.GameTabModel
import com.example.brickui.model.HomeModel
import com.seewo.brick.ktx.data
import com.seewo.brick.ktx.live
import kotlinx.coroutines.Dispatchers

class Tab2ViewModel : BaseViewModel() {

    val banners = emptyList<HomeModel.Banner>().live

    val explore = emptyList<HomeModel.Explore>().live
    val event = HomeModel.Event().live

    val tabs = emptyList<GameTabModel>().live

    val currentTabIndex = 0.live

    init {
        loadData()
    }

    fun loadData() {
        scopeNetLife(Dispatchers.IO) {
            val res = Get<HomeModel>(Api.HOME).await()
            banners.data = res.banner
            explore.data = res.explore
            event.data = res.event
            tabs.data = res.tabs.mapIndexed { index, tab ->
                GameTabModel(tab, index == currentTabIndex.data)
            }
        }
    }

    fun updateTab(model: GameTabModel) {
        val indexOf = tabs.data.indexOf(model)
        if (indexOf < 0) return
        currentTabIndex.data = indexOf
        tabs.data = tabs.data.mapIndexed { index, tab ->
            tab.copy(isSelect = index == indexOf)
        }
    }
}