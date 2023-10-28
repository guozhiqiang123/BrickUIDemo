package com.example.brickui.tab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.scopeNetLife
import androidx.lifecycle.viewmodel.CreationExtras
import com.drake.net.Get
import com.example.brickui.base.BaseViewModel
import com.example.brickui.http.mock.Api
import com.example.brickui.model.GameListModel
import com.example.brickui.model.GameModel
import com.seewo.brick.ktx.data
import com.seewo.brick.ktx.live
import java.util.Random

class TabListViewModel(private val categoryId: Long) : BaseViewModel() {

    val gameModels = emptyList<GameListModel>().live

    var page: Int = 0

    init {
        loadData()
    }

    fun loadData() {
        scopeNetLife {
            val res = Get<GameModel>(Api.GAME) { param("categoryId", categoryId) }.await()
            // shuffled() 为随机打乱列表顺序
            val pageList = res.list.shuffled(Random(categoryId)).map { GameListModel(it) }
            gameModels.data = gameModels.data.toMutableList().apply { addAll(pageList) }
            page += 1
        }
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun create(categoryId: Long): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    return TabListViewModel(categoryId) as T
                }
            }
    }

    fun isLoadAllData() = page >= 3
}