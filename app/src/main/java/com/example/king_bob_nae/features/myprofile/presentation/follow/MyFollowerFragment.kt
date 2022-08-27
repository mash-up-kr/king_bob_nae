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
import com.example.king_bob_nae.features.myprofile.presentation.MyProfileViewModel
import kotlinx.coroutines.launch

class MyFollowerFragment : BaseFragment<FragmentMyFollowerBinding>(R.layout.fragment_my_follower) {
    private val myProfileViewModel: MyProfileViewModel by activityViewModels()
    private val followAdapter by lazy {
        FollowAdapter()
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

}
