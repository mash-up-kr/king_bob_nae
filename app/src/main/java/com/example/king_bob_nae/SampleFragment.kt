package com.example.king_bob_nae

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.king_bob_nae.base.viewDataBinding
import com.example.king_bob_nae.databinding.FragmentSampleBinding

class SampleFragment : Fragment() {

    private val binding by viewDataBinding<FragmentSampleBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSampleFragment.text = "샘플 프래그먼트입니다"
    }
}
