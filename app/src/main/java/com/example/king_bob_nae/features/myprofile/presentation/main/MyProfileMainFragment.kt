package com.example.king_bob_nae.features.myprofile.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyprofileMainBinding
import com.example.king_bob_nae.features.myprofile.domain.UserProfileUiState
import com.example.king_bob_nae.features.myprofile.presentation.MyProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyProfileMainFragment :
    BaseFragment<FragmentMyprofileMainBinding>(R.layout.fragment_myprofile_main) {
    companion object {
        private const val MY_FOLLOWER = 0
        private const val MY_FOLLOWING = 1
    }

    private val myProfileViewModel: MyProfileViewModel by activityViewModels()
    private val myProfileAdapter by lazy {
        MyProfileAdapter(::itemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initCollect()
        initMyProfile()
    }

    fun initView() {
        binding.apply {
            userprofile = UserProfileUiState()
            tvMyProfileTotalFollowerCount.setOnClickListener {
                val action = MyProfileMainFragmentDirections.actionMyProfileMainFragmentToFollowFragment(MY_FOLLOWER)
                it.findNavController().navigate(action)
            }
            tvMyProfileFollower.setOnClickListener {
                val action = MyProfileMainFragmentDirections.actionMyProfileMainFragmentToFollowFragment(MY_FOLLOWER)
                it.findNavController().navigate(action)
            }
            tvMyProfileFollowing.setOnClickListener {
                val action = MyProfileMainFragmentDirections.actionMyProfileMainFragmentToFollowFragment(MY_FOLLOWING)
                it.findNavController().navigate(action)
            }
            tvMyProfileTotalFollowingCount.setOnClickListener {
                val action = MyProfileMainFragmentDirections.actionMyProfileMainFragmentToFollowFragment(MY_FOLLOWING)
                it.findNavController().navigate(action)
            }
            btnMyProfileSetting.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_myProfileMainFragment_to_settingFragment)
            }
            btnMyProfileBack.setOnClickListener {
                requireActivity().finish()
            }
            rvMyProfileScrap.adapter = myProfileAdapter
        }
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    myProfileViewModel.userProfileUiState.collectLatest { userProfileUiState ->
                        binding.userprofile = userProfileUiState
                    }
                }
                launch {
                    myProfileViewModel.userProfileScrapListUiState.collectLatest { scrapList ->
                        myProfileAdapter.submitList(scrapList)
                    }
                }
            }
        }
    }

    private fun initMyProfile() {
        viewLifecycleOwner.lifecycleScope.launch {
            myProfileViewModel.getUserProfile()
        }
    }

    private fun itemClick(scrapedImage: UserProfileUiState.ScrapedImage) {
        val clicked = !scrapedImage.clicked
        if (clicked) {
            Snackbar.make(view!!, "스크랩에서 추가됐어요.", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(view!!, "스크랩에서 삭제됐어요.", Snackbar.LENGTH_SHORT).show()
        }
        myProfileViewModel.updateScrapState(scrapedImage.copy(clicked = clicked))
    }
}
