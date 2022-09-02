package com.example.king_bob_nae.features.mykkilog.presentation.result.presenter

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentKkiLogResultBinding
import com.example.king_bob_nae.features.mykkilog.presentation.result.viewpager.KkilogViewPagerAdapter
import com.example.king_bob_nae.shared.setOnThrottleClickListener
import kotlinx.coroutines.launch

class KkiLogResultFragment :
    BaseFragment<FragmentKkiLogResultBinding>(R.layout.fragment_kki_log_result) {

    private val navArgs by navArgs<KkiLogResultFragmentArgs>()

    private val resultViewModel: KkilogResultViewModel by activityViewModels()
    private val viewPagerAdapter by lazy {
        KkilogViewPagerAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vpFoodImage.adapter = viewPagerAdapter
        binding.dotsIndicator.attachTo(binding.vpFoodImage)

        initCollect()
        initView()
    }

    private fun initView() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.likeCardView.setOnThrottleClickListener {
            // 여기서는 navigate -> 팔로우 팔로워로 이동
            // 요기능은 없는거루
        }
        binding.ivMore.setOnClickListener {
            createDeleteAlertDialog()
        }
        initBookMarkClick()
        initLikeClick()
    }

    private fun createDeleteAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.alert)
            .setMessage("해당 끼록을 삭제하시겠습니까?")
            .setNegativeButton(R.string.alert_no) { _, _ -> }
            .setPositiveButton(R.string.alert_yes) { _, _ ->
                doDelete()
                findNavController().popBackStack()
            }.show()
    }

    private fun doDelete() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                resultViewModel.delteKkilog(navArgs.kkilogId)
            }
        }
    }

    private fun initLikeClick() {
        binding.ivHeart.setOnThrottleClickListener {
            binding.kkilog?.let {
                if (it.isLiked) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            resultViewModel.postKkilogUnLike(navArgs.kkilogId)
                        }
                    }
                } else {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            resultViewModel.postKkilogLike(navArgs.kkilogId)
                        }
                    }
                }
            }
        }
    }

    private fun initBookMarkClick() {
        binding.ivBookmark.setOnThrottleClickListener {
            binding.kkilog?.let {
                if (it.isScraped) {
                    // 스크랩 취소
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            resultViewModel.postKkilogUnScrap(navArgs.kkilogId)
                        }
                    }
                } else {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            resultViewModel.postKkilogScrap(navArgs.kkilogId)
                        }
                    }
                }
            }
        }
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                resultViewModel.getSimpleKkilog(navArgs.kkilogId)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                resultViewModel.kkilogResultUiState.collect {
                    binding.kkilog = it
                    viewPagerAdapter.submitList(it.foodImageList)
                }
            }
        }
    }
}
