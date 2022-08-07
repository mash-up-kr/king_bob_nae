package com.example.king_bob_nae.features.imagepicker.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ImageListViewModel : ViewModel() {
    private val _imageList = MutableStateFlow<List<ImageState>>(emptyList())
    val imageList: StateFlow<List<ImageState>?> = _imageList.asStateFlow() // 모든 이미지 List

    val selectedImageList = MutableStateFlow<MutableList<ImageState>>(mutableListOf()) // 선택된 이미지 List

    fun setImageList(imageList: List<ImageState>) {
        _imageList.value = imageList
    }

    fun updateImageList(image: ImageState) { // 넘어온거 토글해서 저장, 현재 clicked 상태보고 + 1, -1 하면 되고, 0이나 10사이인지 체크하고, 중간값 빠지면 빠진 값부터 위로 -1 씩
        if (image.clicked) {  // 클릭해서 추가한 경우
            //selected size가 10개 미만이면 add 해줌
            if (selectedImageList.value.size < 10) {
                selectedImageList.value.add(image.copy(clickCount = selectedImageList.value.size + 1))
                copyAndUpdateImageList()
            }
        } else {  // 클릭해서 빼는 경우
            // selected size가 1개 이상이면 빼줌
            if (selectedImageList.value.size > 0) {
                selectedImageList.value.remove(image.copy(clicked = true))
                toggleItem(image)
                minusOne(image.clickCount)
                copyAndUpdateImageList()
            }
        }
    }

    private fun toggleItem(image: ImageState) {
        _imageList.update {
            _imageList.value.map { imageData ->
                if (image.imageUrl == imageData.imageUrl) {
                    image.copy(clicked = false)
                } else {
                    imageData
                }
            }
        }
    }

    // click Count 1씩 빼줌
    private fun minusOne(clickCount: Int) {
        for (i in selectedImageList.value.size downTo clickCount) {
            selectedImageList.value[i - 1].clickCount -= 1
        }
    }

    fun copyAndUpdateImageList() {
        _imageList.update {
            _imageList.value.map { imageList ->
                selectedImageList.value.forEach { selectedImage ->
                    if (imageList.imageUrl == selectedImage.imageUrl) {
                        return@map selectedImage
                    }
                }
                imageList
            }
        }
    }

    fun resetAllData() {
        selectedImageList.value = mutableListOf()
        _imageList.update {
            _imageList.value.map {
                it.copy(clicked = false, clickCount = 1)
            }
        }
    }
}
