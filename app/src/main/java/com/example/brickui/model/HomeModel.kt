package com.example.brickui.model

import com.example.brickui.reflect.copyType
import java.io.Serializable


@kotlinx.serialization.Serializable
data class HomeModel(
    var banner: List<Banner> = listOf(),
    var explore: List<Explore> = listOf(),
    var event: Event = Event(),
    var tabs: List<Tab> = listOf()
) : Serializable {

    /** 获取列表有效数据 */
    fun getAvailableData(games: List<GameModel.Data>): MutableList<Any> {
        val data = mutableListOf<Any>()
        // 如果不使用copyType, 则会导致BRV无法区分List<Banner>和List<Explore>区别, Java泛型擦除问题
        // 或者定义不同类的List, 如BannerList<T>和ExploreList<T>也是可以区分的
        if (banner.isNotEmpty()) data.add(banner.copyType())
        if (explore.isNotEmpty()) data.add(explore.copyType())
        if (event.id != 0L) data.add(event)
        data.add("最新折扣")

        if (games.isEmpty()) {

        } else {
            data.addAll(games) // 游戏列表
        }
        return data
    }

    @kotlinx.serialization.Serializable
    data class Banner(
        var id: Long = 0,
        var image: String = "",
    ) : Serializable

    @kotlinx.serialization.Serializable
    data class Explore(
        var id: Long = 0,
        var image: String = "",
    ) : Serializable

    @kotlinx.serialization.Serializable
    data class Event(
        var id: Long = 0,
        var image: String = "",
    ) : Serializable

    @kotlinx.serialization.Serializable
    data class Tab(
        var id: Long = 0,
        var title: String = "",
        var count: Int = 0
    ) : Serializable
}