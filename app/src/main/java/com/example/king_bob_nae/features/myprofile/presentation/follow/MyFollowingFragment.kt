package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyFollowingBinding
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.example.king_bob_nae.features.myprofile.presentation.MyProfileViewModel
import kotlinx.coroutines.launch

class MyFollowingFragment :
    BaseFragment<FragmentMyFollowingBinding>(R.layout.fragment_my_following) {
    private val myProfileViewModel: MyProfileViewModel by activityViewModels()
    private val followAdapter by lazy {
        FollowAdapter(::itemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
        initCollect()
    }

    private fun initView() {
        binding.rvFollowingList.adapter = followAdapter
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myProfileViewModel.getUserFollow(type = "following")
            }
        }
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myProfileViewModel.followListUiState.collect {
                    followAdapter.submitList(it)
                }
            }
        }
    }

    private fun itemClick(item: UsersFollowUiState) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (item.following) {
                    myProfileViewModel.doUnFollow(item)
                } else {
                    myProfileViewModel.doFollow(item)
                }
            }
        }
    }

}
