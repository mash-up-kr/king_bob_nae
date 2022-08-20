package com.example.king_bob_nae.features.home.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentHomeBinding
import com.example.king_bob_nae.features.home.presentation.adapter.UserListAdapter
import com.example.king_bob_nae.features.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val userListAdapter by lazy { UserListAdapter() }

    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlows()
        initApiCall()
    }

    private fun initApiCall() {
        with(homeViewModel) {
            getFriendList()
            getHomeStatus()
        }
    }

    private fun initView() {
        binding.commonHomeLayout.rvFriends.apply {
            adapter = userListAdapter
        }
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeUserFriendList.collect {
                    userListAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userList.collectLatest {
                    binding.home = it
                    binding.commonHomeLayout.home = it
                }
            }
        }
    }
}
