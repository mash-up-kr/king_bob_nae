package com.example.king_bob_nae.features.imagepicker.presentation

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentImagePickerBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class ImagePickerFragment :
    BaseFragment<FragmentImagePickerBinding>(R.layout.fragment_image_picker) {
    private val imageAdapter by lazy {
        ImageListAdapter(::itemClick)
    }
    private val imageListViewModel: ImageListViewModel by activityViewModels()
    private lateinit var registerPictureLauncher: ActivityResultLauncher<Uri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPhotoAlbumList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRegister()
        initBinding()
        initSubmitList()
        initClickListener()
    }

    private fun initRegister() {
        registerPictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                findNavController().navigate(R.id.kkiLogFragment)
            }
        }
    }

    /** (2022- 07 - 31 )
    할일
    1. 사진 사이 조정하기 ( 해야됨 )
    2. 이미지 넘길 때 어떻게 할지 정하기 (이미지 한개는 Bitmap, 여러개는 String 일예정) x -> 그냥 위치값 바로 저장
    3. 뷰모델 사진 numbering 구현하기 ( ex) 마지막 count 값 가지고 있고, 현재 뺀 값이 더 적다면 마지막 count까지 -1해주기기
    4. 얘기해서 q이상으로해야할지 정하기
     */

    private fun initClickListener() {
        with(binding) {
            btnImagePickerNext.setOnClickListener {
                /**
                 * 여긴 다음 버튼 눌렀을 때
                 * 즉, 여러개의 데이터를 (사진 array 를 넘겨야 할 때)
                 */
                if (imageListViewModel.selectedImageList.value.size > 0) {
                    findNavController().navigate(R.id.kkiLogFragment)
                    imageListViewModel.resetAllData()
                }
            }
            ivTakeAPhoto.setOnClickListener {
                /**
                 * 여긴 이미지 찍고 한개만 넘길 때, 즉 initRegister 안의 코드가 실행되어 viewmodel에 사진 딱 한개만 저장될 때
                 */
                val uri = requireContext().getCacheFileProviderImageUri()
                registerPictureLauncher.launch(uri)
            }
        }
    }

    fun Context.getCacheFileProviderImageUri(): Uri {
        var image: File? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss")
            val filename = "IMG_${dateFormat.format(Date(System.currentTimeMillis()))}.jpg"
            val file = File(Environment.getExternalStorageDirectory().path + "/Pictures/", filename)
            file.mkdir()
            image = File(file, filename)
        } else {
            val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss")
            val filename = "IMG_${dateFormat.format(Date(System.currentTimeMillis()))}.jpg"
            val dir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                "Camera"
            )
            dir.mkdir()
            image = File(dir, filename) // 요로케 해서 launch로 넘겨버리기 분기처리없이

            // Insert my file to MediaStore
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
                put(MediaStore.Images.Media.DATA, dir.toString())
            }
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        }

        return FileProvider.getUriForFile(this, "${applicationContext.packageName}.provider", image)
            .also { uri ->
                grantUriPermission(
                    applicationContext.packageName,
                    uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
    }

    // 민욱이랑 얘기해보고 모르면 걍 q 이상부터 ㅎㅎ; ㅈㅅ
    private fun initSubmitList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                kotlin.runCatching {
                    binding.image = imageListViewModel.imageList.value?.get(0)
                }.onFailure {
                    Toast.makeText(requireContext(), "사진이 없거나 권한이 없습니다!", Toast.LENGTH_SHORT).show()
                    binding.image = ImageState(imageUrl = "a")
                }
                imageListViewModel.imageList.collectLatest { imageList ->
                    imageAdapter.submitList(imageList)
                }
            }
        }
    }

    private fun initBinding() {
        with(binding) {
            rvImageList.adapter = imageAdapter
            rvImageList.addItemDecoration(ImagePickerGridDivider())
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun getSize(): Int {
        val displayMetrics: DisplayMetrics = this.resources.displayMetrics
        val width = displayMetrics.widthPixels
        val dividers = (3 + 1) * 0.02
        return (width / (3 + dividers)).roundToInt()
    }

    private fun getPhotoAlbumList() {
        val listOfAllImages = mutableListOf<ImageState>()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val sortType = MediaStore.MediaColumns.DATE_ADDED + " DESC"

        requireActivity().contentResolver.query(
            uri,
            projection,
            null,
            null,
            sortType
        )?.use { cursor ->
            val columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            while (cursor.moveToNext()) {
                val columnString = cursor.getString(columnIndexData)

                listOfAllImages.add(
                    ImageState(
                        imageUrl = columnString
                    )
                )
            }
            cursor.close()
        }
        imageListViewModel.setImageList(listOfAllImages)
    }

    private fun itemClick(imageState: ImageState) {
        if (imageListViewModel.selectedImageList.value.size < 10) {
            binding.image = imageState
        }
        imageListViewModel.updateImageList(imageState.copy(clicked = !imageState.clicked))
        imageAdapter.notifyDataSetChanged()
    }
}
