package com.example.king_bob_nae.features.create.kkilog.presenter

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
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
import com.example.king_bob_nae.utils.isEnabled
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        kkiLogViewModel.checkTitleEmpty(true)
        binding.apply {
            rvPhoto.adapter = foodListAdapter
            ivBack.setOnClickListener {
                clearList()
                kkiLogViewModel.checkTitleEmpty(true)
                findNavController().popBackStack()
            }
            btnFinish.apply {
                setOnClickListener {
                    kkiLogViewModel.upLoadKkiLog(
                        binding.etFoodName.text.toString(),
                        binding.etFoodDescription.text.toString(),
                        binding.etMyKick.text.toString()
                    )
                }
            }
            etFoodName.doOnTextChanged { text, start, before, count ->
                kkiLogViewModel.checkTitleEmpty(text.isNullOrBlank())
            }
        }
    }

    private fun clearList() {
        kkiLogViewModel.clearList()
        kkiLogViewModel.clearSaved()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    kkiLogViewModel.imageList.collectLatest { foodList ->
                        foodListAdapter.submitList(foodList.toList())
                        kkiLogViewModel.checkImageEmpty((foodList.count() == 1))
                    }
                }
                launch {
                    kkiLogViewModel.isValidFormat.collectLatest { validCheck ->
                        if (validCheck.isImageEmpty or validCheck.isTitleEmpty)
                            binding.btnFinish.isEnabled(false)
                        else
                            binding.btnFinish.isEnabled(true)
                    }
                }
                launch {
                    kkiLogViewModel.savedKkilog.collect { uiState ->
                        binding.apply {
                            etFoodName.setText(uiState.title)
                            etFoodDescription.setText(uiState.description)
                            etMyKick.setText(uiState.kick)
                        }
                    }
                }
                launch {
                    kkiLogViewModel.kkilogId.collect {
                        val action =
                            KkiLogFragmentDirections.actionKkiLogFragmentToKkilogResultFragment(it)
                        findNavController().navigate(action)
                    }
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
            kkiLogViewModel.saveKkilog(
                binding.etFoodName.text.toString(),
                binding.etFoodDescription.text.toString(),
                binding.etMyKick.text.toString()
            )
        }
    }

    private fun handlingBackPressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                kkiLogViewModel.clearList()
                kkiLogViewModel.checkTitleEmpty(true)
                findNavController().popBackStack()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
