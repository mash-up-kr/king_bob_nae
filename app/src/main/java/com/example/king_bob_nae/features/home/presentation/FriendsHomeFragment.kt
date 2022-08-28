package com.example.king_bob_nae.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentFriendsHomeBinding
import com.example.king_bob_nae.features.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendsHomeFragment :
    BaseFragment<FragmentFriendsHomeBinding>(R.layout.fragment_friends_home) {
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlows()
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                homeViewModel.run {
                    launch {
                        goHomeFragmentEvent.collect {
                            val action =
                                FriendsHomeFragmentDirections.actionFriendsHomeFragmentToHomeFragment(
                                    it
                                )
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }
}
