package com.example.brickui.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.brickui.base.BaseFragment

class Tab2Fragment : BaseFragment() {

    private val viewModel: Tab2ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = requireContext().tab2FragmentUI(
        bannerData = viewModel.banners,
        exploreData = viewModel.explore,
        eventData = viewModel.event,
        tabsData = viewModel.tabs,
        currentTabIndex = viewModel.currentTabIndex,
        onRefresh = { refresh ->
            viewModel.banners.observe(viewLifecycleOwner) {
                refresh.finishRefresh()
            }
            viewModel.loadData()
        },
        onTabPageChanged = {
            viewModel.updateTab(it)
        })
}
