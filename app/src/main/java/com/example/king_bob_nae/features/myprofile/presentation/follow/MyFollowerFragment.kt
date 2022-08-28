package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyFollowerBinding
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.example.king_bob_nae.features.myprofile.presentation.MyProfileViewModel
import kotlinx.coroutines.launch

class MyFollowerFragment : BaseFragment<FragmentMyFollowerBinding>(R.layout.fragment_my_follower) {
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
        binding.rvFollowerList.adapter = followAdapter
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myProfileViewModel.getUserFollow(type = "follower")
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
                    /**
                     * 팔로우가 follow 상태일 때 unfollow가 성공하면 들어감
                     * 성공시 uiState follow를 false로 바꿔줌 (현재는 true)
                     */
                    myProfileViewModel.doUnFollow(item)
                } else {
                    myProfileViewModel.doFollow(item)
                }
            }
        }
    }

}
