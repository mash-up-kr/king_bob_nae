package com.example.king_bob_nae.features.create.detail.presentaion.write

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentDetailKkiLogBinding
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.features.create.detail.presentaion.DetailKkiLogViewModel
import com.example.king_bob_nae.features.create.detail.presentaion.write.adapter.DetailKkiLogIngredientAdapter
import com.example.king_bob_nae.features.create.detail.presentaion.write.adapter.DetailKkiLogRecipeAdapter
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

    private val detailKkiLogViewModel by activityViewModels<DetailKkiLogViewModel>()
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
        requestPermission()
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
                            if (it) {
                                binding.tvEdit.text = "완료"
                            } else {
                                binding.tvEdit.text = "편집"
                            }
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

                    launch {
                        detailKkiLogResult.collect {
                            NLog.d("kelly result", it.toString())
                            if (it.id != 0) {
                                val action =
                                    DetailKkiLogFragmentDirections.actionDetailKkiLogFragmentToDetailKkiLogResultFragment(
                                        userId = it.id
                                    )
                                findNavController().navigate(action)
                            }
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
        detailKkiLogViewModel.setKKiLogImagePosition("recipe")
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

                    if (detailKkiLogViewModel.kkiLogImagePosition == "recipe") {
                        val body =
                            MultipartBody.Part.createFormData(
                                "recipeImages",
                                file.name,
                                requestFile
                            )
                        detailKkiLogViewModel.setRecipeImage(currentImageUri, body)
                    } else {
                        val body =
                            MultipartBody.Part.createFormData("brandImage", file.name, requestFile)
                        detailKkiLogViewModel.setBrandImage(currentImageUri, body)
                    }
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show()
                } else {
                    NLog.d("Kelly select picture error", "")
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

    private fun requestPermission() {
        val REQUEST_EXTERNAL_STORAGE = 1
        val PERMISSIONS_STORAGE = arrayOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val permission = ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            itemTouchHelper.startDrag(it)
        }
    }
}
