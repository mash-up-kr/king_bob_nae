package com.example.king_bob_nae.features.create.detail.presentaion

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogResultBinding

class DetailKkiLogResultFragment :
    BaseFragment<FragmentDetailKkiLogResultBinding>(R.layout.fragment_detail_kki_log_result) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            ivMore.setOnClickListener { view ->
                PopupMenu(root.context, view).apply {
                    menuInflater.inflate(R.menu.menu_kki_log_feed, this.menu)
                    // setOnMenuItemClickListener {  }
                }.show()
            }
        }
    }
}
