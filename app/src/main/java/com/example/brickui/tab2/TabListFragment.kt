package com.example.brickui.tab2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.brickui.base.BaseFragment
import com.example.brickui.ext.toast
import com.example.brickui.model.GameTabModel
import com.seewo.brick.ktx.bindNotNull

class TabListFragment : BaseFragment() {

    private val viewModel: TabListViewModel by viewModels {
        val gameTabModel = arguments?.getSerializable(ARG_DATA) as? GameTabModel
        TabListViewModel.create(gameTabModel?.tab?.id ?: 0L)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = requireContext().TabListUI(viewModel.gameModels, onLoadMore = { refresh ->
        viewModel.gameModels.bindNotNull(requireContext()) {
            if (viewModel.isLoadAllData()) {
                refresh.finishLoadMoreWithNoMoreData()
            } else {
                refresh.finishLoadMore()
            }
        }
        viewModel.loadData()
    }) { data, position ->
        requireContext().toast("点击了游戏商品：${data.gameModel.name}")
    }

    companion object {
        const val ARG_DATA = "data"
    }
}