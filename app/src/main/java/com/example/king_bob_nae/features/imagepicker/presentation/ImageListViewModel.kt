package com.example.king_bob_nae.features.imagepicker.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ImageListViewModel : ViewModel() {
    private val _imageList = MutableStateFlow<List<ImageState>>(emptyList())
    val imageList = _imageList.asStateFlow()

    fun setImageList(imageList: List<ImageState>) {
        _imageList.value = imageList
    }

    fun updateImageList(image: ImageState) {
        val temporaryImageList = mutableListOf<ImageState>()
        _imageList.value.forEach {
            if (it.imageUrl == image.imageUrl) {
                temporaryImageList.add(image)
            } else {
                temporaryImageList.add(it)
            }
        }
        _imageList.update {
            temporaryImageList
        }
    }
}
