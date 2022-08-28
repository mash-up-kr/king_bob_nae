package com.example.king_bob_nae.features.myprofile.presentation.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentFollowingBinding
import com.example.king_bob_nae.features.myprofile.presentation.MyProfileViewModel
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import com.example.king_bob_nae.utils.afterTextChangedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class FollowingFragment : BaseFragment<FragmentFollowingBinding>(R.layout.fragment_following) {
    companion object {
        const val DEBOUNCE = 1000L
    }

    private val myProfileViewModel: MyProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initCollect()
        initSearch()
    }

    private fun initView() {
        with(binding) {
            btnFollowingBack.setOnClickListener {
                it.findNavController().popBackStack()
            }
            btnSearchFollow.setOnThrottleClickListener {
                friend?.let {
                    if (friend?.following != false) {
                        myProfileViewModel.friendsDoUnFollow(friend)
                    } else {
                        myProfileViewModel.friendsDoFollow(friend)
                    }
                }
            }
        }
    }

    private fun initSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.tfEtFriendNickname.afterTextChangedFlow()
                .debounce(DEBOUNCE)
                .filter { it.toString().isNotBlank() }
                .collect { searchText ->
                    myProfileViewModel.searchFriend("follower", searchText.toString()) {
                        binding.cvSearch.visibility = View.VISIBLE
                    }
                }
        }
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myProfileViewModel.searchFriendState.collect {
                    binding.friend = it
                }
            }
        }
    }
}
