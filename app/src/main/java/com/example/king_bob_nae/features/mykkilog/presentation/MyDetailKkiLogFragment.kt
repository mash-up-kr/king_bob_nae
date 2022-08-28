package com.example.king_bob_nae.features.mykkilog.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentMyDetailKkilogBinding
import com.example.king_bob_nae.features.mykkilog.data.MyKkiLog

class MyDetailKkiLogFragment :
    BaseFragment<FragmentMyDetailKkilogBinding>(R.layout.fragment_my_detail_kkilog) {
    private val itemAdapter: MyKkiLogAdapter by lazy { MyKkiLogAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val item1 = MyKkiLog(title = "죽이는 김치", date = "2022.08.24")
        val item2 = MyKkiLog(title = "씹노맛 김치", date = "2022.08.24")
        val item3 = MyKkiLog(title = "죽이는 김치", date = "2022.08.24")
        val item4 = MyKkiLog(title = "현수네 김치", date = "2022.08.24")
        val item5 = MyKkiLog(title = "존맛탱 김치", date = "2022.08.24")
        val item6 = MyKkiLog(title = "썩은 김치", date = "2022.08.24")
        val itemList: List<MyKkiLog> = listOf(item1, item2, item3, item4, item5, item6)
        binding.rvMyDetailKkilog.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = itemAdapter
        }
        itemAdapter.submitList(itemList.toList())
    }
}