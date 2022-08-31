package com.example.king_bob_nae.features.create.kkilog.presenter

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentKkiLogBinding
import com.example.king_bob_nae.features.create.kkilog.presenter.adapter.FoodListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class KkiLogFragment : BaseFragment<FragmentKkiLogBinding>(R.layout.fragment_kki_log) {
    private val kkiLogViewModel: KkiLogViewModel by activityViewModels()
    private val foodListAdapter by lazy {
        FoodListAdapter(kkiLogViewModel, itemClickListener = {
            doOnClick()
        })
    }
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handlingBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        getImageList()
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {
            rvPhoto.adapter = foodListAdapter
            ivBack.setOnClickListener {
                kkiLogViewModel.clearList()
                findNavController().popBackStack()
            }
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                kkiLogViewModel.imageList.collectLatest { foodList ->
                    foodListAdapter.submitList(foodList.toList())
                }
            }
        }
    }

    private fun getImageList() {
        val args: KkiLogFragmentArgs by navArgs()
        args.imageList?.toCollection(ArrayList())?.let { kkiLogViewModel.setImageList(it) }
    }

    private fun doOnClick() {
        if (!kkiLogViewModel.isMaxCount()) {
            val action =
                KkiLogFragmentDirections.actionKkiLogFragmentToImagePickerFragment(
                    kkiLogViewModel.getListCount()
                )
            findNavController().navigate(action)
        }
    }

    private fun handlingBackPressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                kkiLogViewModel.clearList()
                findNavController().popBackStack()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
