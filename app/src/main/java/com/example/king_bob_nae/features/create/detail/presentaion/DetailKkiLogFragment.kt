package com.example.king_bob_nae.features.create.detail.presentaion

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogBinding
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.adapter.DetailKkiLogIngredientAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.adapter.DetailKkiLogRecipeAdapter
import com.example.king_bob_nae.utils.NLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class DetailKkiLogFragment :
    BaseFragment<FragmentDetailKkiLogBinding>(R.layout.fragment_detail_kki_log),
    RecipeItemDragListener {

    private val detailKkiLogViewModel by viewModels<DetailKkiLogViewModel>()
    private val detailKkiLogIngredientAdapter by lazy {
        DetailKkiLogIngredientAdapter(
            detailKkiLogViewModel
        )
    }
    private val detailKkiLogRecipeAdapter by lazy {
        DetailKkiLogRecipeAdapter(
            detailKkiLogViewModel,
            ::addRecipeImage,
            this
        )
    }

    private val callback: ItemTouchHelper.Callback by lazy {
        DetailKkiLogReorderCallback(detailKkiLogRecipeAdapter)
    }
    private val itemTouchHelper: ItemTouchHelper by lazy {
        ItemTouchHelper(callback)
    }

    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlows()
    }

    init {
        takeOnePicture()
    }

    private fun initView() {

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.viewModel = detailKkiLogViewModel

        binding.run {
            rvIngredient.adapter = detailKkiLogIngredientAdapter
            rvRecipe.apply {
                adapter = detailKkiLogRecipeAdapter
                itemAnimator = null
            }
            itemTouchHelper.attachToRecyclerView(rvRecipe)

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

                    launch {
                        isEditMode.collect {
                            if (!it) {
                                binding.tvEdit.text = "완료"
                            } else {
                                binding.tvEdit.text = "편집"
                            }
                            detailKkiLogViewModel.changeEditMode()
                        }
                    }

                    launch {
                        setKkiLogImageEvent.collect {
                            val intent = Intent(Intent.ACTION_PICK).apply {
                                type = "image/*"
                            }
                            activityLauncher.launch(intent)
                        }
                    }

                    launch {
                        showToastMessage.collect {
                            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
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
        activityLauncher.launch(intent)
        detailKkiLogViewModel.setRecipeItem(item)
    }

    private fun takeOnePicture() {
        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK && it.data != null) {
                    val currentImageUri = it.data?.data
                    val file = File(changeToAbsolutePath(currentImageUri, requireContext()))
                    val requestFile = file.asRequestBody(("image/*".toMediaTypeOrNull()))
                    val body = MultipartBody.Part.createFormData("proFile", file.name, requestFile)
                    detailKkiLogViewModel.setImage(currentImageUri, body)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show()
                } else {
                    NLog.d("Kelly", "error")
                }
            }
    }

    fun changeToAbsolutePath(path: Uri?, context: Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        val result = c?.getString(index ?: 0)

        return result ?: ""
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            itemTouchHelper.startDrag(it)
        }
    }
}
