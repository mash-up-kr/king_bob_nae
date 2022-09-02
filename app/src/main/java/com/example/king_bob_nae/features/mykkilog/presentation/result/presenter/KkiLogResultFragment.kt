package com.example.king_bob_nae.features.mykkilog.presentation.result.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentKkiLogResultBinding
import kotlinx.coroutines.launch

class KkiLogResultFragment :
    BaseFragment<FragmentKkiLogResultBinding>(R.layout.fragment_kki_log_result) {
    private val navArgs by navArgs<KkiLogResultFragmentArgs>()

    // 받은 args로 끼록에 대한 정보 받아서 보여주고, 조아요나 삭제 기능 넣을 생각해야함

    private val viewmodel: KkilogResultViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.test(3, 24)
        }
    }
}
