package com.example.king_bob_nae.features.imagepicker.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentImagePickerBinding
import com.example.king_bob_nae.features.create.kkilog.presenter.KkiLogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ImagePickerFragment :
    BaseFragment<FragmentImagePickerBinding>(R.layout.fragment_image_picker) {
    private val adapter by lazy {
        ImageListAdapter(::itemClick)
    }
    private val imageListViewModel: ImageListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        val readExternalPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val writeExternalPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val cameraPermission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)

        if (readExternalPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();
            //사용자가 거부를 한 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(
                    requireContext(),
                    "외부 저장소 접근을 위해 저장소 접근 권한이 필요합니다.",
                    Toast.LENGTH_LONG
                ).show();
            }
            //최초 요청
            else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                );
                Toast.makeText(
                    requireContext(),
                    "외부 저장소 접근을 위해 저장소 접근 권한이 필요합니다.",
                    Toast.LENGTH_LONG
                ).show();
            }
        }

        if (writeExternalPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();
            //사용자가 거부를 한 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(
                    requireContext(),
                    "외부 저장소 접근을 위해 저장소 접근 권한이 필요합니다.",
                    Toast.LENGTH_LONG
                ).show();
            }
            //최초 요청
            else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1000
                );
                Toast.makeText(
                    requireContext(),
                    "외부 저장소 접근을 위해 저장소 접근 권한이 필요합니다.",
                    Toast.LENGTH_LONG
                ).show();
            }
        }

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();
            //사용자가 거부를 한 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.CAMERA
                )
            ) {
                Toast.makeText(
                    requireContext(),
                    "외부 저장소 접근을 위해 저장소 접근 권한이 필요합니다.",
                    Toast.LENGTH_LONG
                ).show();
            }
            //최초 요청
            else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    1000
                );
                Toast.makeText(
                    requireContext(),
                    "외부 저장소 접근을 위해 저장소 접근 권한이 필요합니다.",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageListViewModel.setImageList(getImageFilesUrl())
        initBinding(imageListViewModel.imageList.value)
        initSubmitList()
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnImagePickerNext.setOnClickListener {
            parentFragmentManager.commit {
                replace<KkiLogFragment>(R.id.frame_layout)
                addToBackStack("")
            }
        }
    }

    private fun initSubmitList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                imageListViewModel.imageList.collectLatest { imageList ->
                    adapter.submitList(imageList)
                }
            }
        }
    }

    private fun initBinding(imageList: List<ImageState>) {
        with(binding) {
            rvImageList.adapter = adapter
            image = ImageState(imageUrl = imageList[0].imageUrl)
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun getImageFilesUrl(): List<ImageState> {
        val listOfAllImages = mutableListOf<ImageState>()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val sortType = MediaStore.MediaColumns.DATE_ADDED + " DESC"

        requireContext().contentResolver.query(
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
        return listOfAllImages
    }

    private fun itemClick(imageState: ImageState) {
        binding.image = imageState
        imageListViewModel.updateImageList(imageState.copy(clicked = !imageState.clicked))
    }
}
