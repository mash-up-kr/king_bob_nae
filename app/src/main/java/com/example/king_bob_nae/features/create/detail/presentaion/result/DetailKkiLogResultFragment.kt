package com.example.king_bob_nae.features.create.detail.presentaion.result

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogResultBinding
import com.example.king_bob_nae.features.create.detail.presentaion.result.adapter.ResultIngredientAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.result.adapter.ResultRecipeAdapter
import com.example.king_bob_nae.features.myprofile.presentation.follow.FollowFragmentArgs
import kotlinx.coroutines.launch

class DetailKkiLogResultFragment :
    BaseFragment<FragmentDetailKkiLogResultBinding>(R.layout.fragment_detail_kki_log_result) {
    private val detailKkiLogResultViewModel by activityViewModels<DetailKkiLogResultViewModel>()
    private val safeArgs by navArgs<FollowFragmentArgs>()

    private val resultIngredientAdapter by lazy {
        ResultIngredientAdapter(
            detailKkiLogResultViewModel
        )
    }
    private val resultRecipeAdapter by lazy {
        ResultRecipeAdapter(
            detailKkiLogResultViewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlows()
        fetchDetailKkiLog()
    }

    private fun initView() {
        binding.viewModel = detailKkiLogResultViewModel

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.run {
            ivMore.setOnClickListener { view ->
                PopupMenu(root.context, view).apply {
                    menuInflater.inflate(R.menu.menu_kki_log_feed, this.menu)
                    setOnMenuItemClickListener { // TODO menu item click 처리
                        true
                    }
                }.show()
            }

            ivBack.setOnClickListener {
                navController.popBackStack()
            }

            rvIngredient.adapter = resultIngredientAdapter
            rvRecipe.adapter = resultRecipeAdapter
        }
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailKkiLogResultViewModel.run {
                    launch {
                    }
                }
            }
        }
    }

    private fun fetchDetailKkiLog() {
        val args: DetailKkiLogResultFragmentArgs by navArgs()
        detailKkiLogResultViewModel.setKkiLogUserId(args.userId)
    }
}
