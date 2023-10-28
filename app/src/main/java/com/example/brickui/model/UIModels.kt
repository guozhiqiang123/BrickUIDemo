package com.example.brickui.model

import com.seewo.brick.params.RecyclerItemData
import java.io.Serializable

data class MainMenuModel(val name: String, val icon: Int, val size: String)

@kotlinx.serialization.Serializable
data class GameTabModel(val tab: HomeModel.Tab, val isSelect: Boolean) : Serializable


data class GameListModel(val gameModel: GameModel.Data) : RecyclerItemData {
    override fun areSameItem(out: Any?): Boolean = false
}