package com.example.king_bob_nae.features.mykkilog.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyDetailKkilogBinding
import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyDetailKkiLogFragment :
    BaseFragment<FragmentMyDetailKkilogBinding>(R.layout.fragment_my_detail_kkilog) {
    private val itemAdapter: MyKkiLogAdapter by lazy { MyKkiLogAdapter() }

    private val myKkiLogViewModel: MyKkiLogViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlow()
        initView()
    }

    private fun initView() {
        val tmpUrl =
            "https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/039/143/original/IMG_5649%E3%81%AE%E3%82%B3%E3%83%92%E3%82%9A%E3%83%BC.jpg?2019&d=750x400"
        val item1 = MyKkiLogThumbNail(0, tmpUrl, "죽이는 김치", "2022.08.24", false)
        val item2 = MyKkiLogThumbNail(1, tmpUrl, "씹노맛 김치", "2022.08.24", false)
        val item3 = MyKkiLogThumbNail(2, tmpUrl, "음 쥑이는 김치", "2022.08.24", false)
        val item4 = MyKkiLogThumbNail(3, tmpUrl, "현수네 김치", "2022.08.24", false)
        val item5 = MyKkiLogThumbNail(4, tmpUrl, "존맛탱 김치", "2022.08.24", false)
        val item6 = MyKkiLogThumbNail(5, tmpUrl, "썩은 김치", "2022.08.24", false)
        val itemList: List<MyKkiLogThumbNail> = listOf(item1, item2, item3, item4, item5, item6)
        binding.rvMyDetailKkilog.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = itemAdapter
        }
        itemAdapter.submitList(itemList.toList())
    }

    fun showEmptyMark(state: Boolean) {
        binding.apply {
            tvEmpty.isVisible = state
            ivEmptyMark.isVisible = state
        }
    }

    private fun collectFlow(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myKkiLogViewModel.myDetailKkiLog.collectLatest { myDetailKkiLogList ->
                    showEmptyMark(myDetailKkiLogList.isNullOrEmpty())
                    itemAdapter.submitList(myDetailKkiLogList?.toList())
                }
            }
        }
    }
}