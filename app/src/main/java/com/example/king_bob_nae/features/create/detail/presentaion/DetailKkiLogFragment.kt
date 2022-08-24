package com.example.king_bob_nae.features.create.detail.presentaion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogBinding
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.adapter.DetailKkiLogIngredientAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.adapter.DetailKkiLogRecipeAdapter
import com.example.king_bob_nae.utils.NLog
import kotlinx.coroutines.launch

class DetailKkiLogFragment :
    BaseFragment<FragmentDetailKkiLogBinding>(R.layout.fragment_detail_kki_log) {

    private val detailKkiLogViewModel by viewModels<DetailKkiLogViewModel>()
    private val detailKkiLogIngredientAdapter by lazy {
        DetailKkiLogIngredientAdapter(
            detailKkiLogViewModel
        )
    }
    private val detailKkiLogRecipeAdapter by lazy {
        DetailKkiLogRecipeAdapter(
            detailKkiLogViewModel,
            ::addRecipeImage
        )
    }

    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlows()
    }

    private fun initView() {

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.run {
            rvIngredient.adapter = detailKkiLogIngredientAdapter
            rvRecipe.adapter = detailKkiLogRecipeAdapter

            ivClose.setOnClickListener {
                navController.popBackStack()
            }

            tvFinish.setOnClickListener {
                navController.navigate(R.id.action_detailKkiLogFragment_to_detailKkiLogResultFragment)
            }

            tvAddIngredient.setOnClickListener {
                detailKkiLogViewModel.addIngredient()
            }

            tvAddRecipe.setOnClickListener {
                detailKkiLogViewModel.addRecipe()
            }

            ivFoodImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                takeOnePicture("kkiLog")
                activityLauncher.launch(intent)
            }

            etIntroduce.doOnTextChanged { text, _, _, _ ->
                detailKkiLogViewModel.setDescriptionLength(text.toString())
            }

            etFoodTitle.apply {
                doAfterTextChanged { title ->
                    detailKkiLogViewModel.setKkiLogTitle(title.toString())
                }
            }
        }
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailKkiLogViewModel.run {
                    launch {
                        ingredientList.collect {
                            detailKkiLogIngredientAdapter.submitList(it)
                        }
                    }
                    launch {
                        recipeList.collect {
                            detailKkiLogRecipeAdapter.submitList(it)
                        }
                    }
                }
            }
        }
    }

    private fun addRecipeImage(item: KkiLogRecipe) {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        takeOnePicture("recipe")
        activityLauncher.launch(intent)
        detailKkiLogViewModel.setRecipeItem(item)
    }

    private fun takeOnePicture(type: String) {
        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK && it.data != null) {
                    val currentImageUri = it.data?.data
                    when (type) {
                        "kkiLog" -> {
                            detailKkiLogViewModel.setKkiLogImage(currentImageUri)
                        }
                        "recipe" -> {
                            detailKkiLogViewModel.addRecipeImage(currentImageUri)
                        }
                    }
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show()
                } else {
                    NLog.d("Kelly", "error")
                }
            }
    }
}
