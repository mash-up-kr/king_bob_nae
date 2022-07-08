package com.example.king_bob_nae.features.imagepicker.presentation

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentImagePickerBinding


class ImagePickerFragment : BaseFragment<FragmentImagePickerBinding>(R.layout.fragment_image_picker) {
    private val adapter by lazy {
        ImageListAdapter(::itemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvImageList.adapter = adapter

        adapter.submitList(getImageFilesUrl())
    }

    private fun getImageFilesUrl(): List<ImageState> {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DATE_ADDED)
        val sortType = MediaStore.MediaColumns.DATE_ADDED + " DESC"

        val cursor = requireContext().contentResolver.query(
            uri,
            projection,
            null,
            null,
            sortType
        ) ?: return mutableListOf()

        val listOfAllImages = mutableListOf<ImageState>()
        val columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)

        while (cursor.moveToNext()) {
            listOfAllImages.add(
                ImageState(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)),
                    imageUrl = cursor.getString(columnIndexData)
                )
            )
        }
        cursor.close()
        return listOfAllImages
    }

    private fun itemClick(clickedItem: ImageState) {

    }
}
