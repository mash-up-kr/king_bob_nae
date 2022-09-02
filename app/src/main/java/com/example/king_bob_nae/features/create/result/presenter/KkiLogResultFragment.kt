package com.example.king_bob_nae.features.create.result.presenter

import androidx.navigation.fragment.navArgs
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentKkiLogResultBinding

class KkiLogResultFragment :
    BaseFragment<FragmentKkiLogResultBinding>(R.layout.fragment_kki_log_result) {
    private val navArgs by navArgs<KkiLogResultFragmentArgs>()

    // 받은 args로 끼록에 대한 정보 받아서 보여주고, 조아요나 삭제 기능 넣을 생각해야함
}
