package com.example.king_bob_nae.features.create.kkilog.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.kkilog.data.KkiLogRecipe
import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadDto
import com.example.king_bob_nae.features.create.kkilog.data.getImageUrl
import com.example.king_bob_nae.features.create.kkilog.domain.SavedKkilog
import com.example.king_bob_nae.features.create.kkilog.domain.UpLoadKkiLogUseCase
import com.example.king_bob_nae.features.create.kkilog.domain.ValidCheck
import com.example.king_bob_nae.features.create.kkilog.presenter.adapter.FIRST_HOLDER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KkiLogViewModel @Inject constructor(
    private val upLoadKkiLogUseCase: UpLoadKkiLogUseCase
) : ViewModel() {

    private val _imageList =
        MutableStateFlow<List<KkiLogRecipe>>(listOf(KkiLogRecipe(FIRST_HOLDER)))
    val imageList = _imageList.asStateFlow()

    val isValidFormat = MutableStateFlow(ValidCheck())

    val savedKkilog = MutableStateFlow(SavedKkilog())

    fun setImageList(imageList: ArrayList<KkiLogRecipe>) {
        _imageList.value = _imageList.value + imageList
    }

    fun clearList() {
        _imageList.update {
            listOf(KkiLogRecipe(FIRST_HOLDER))
        }
    }

    fun removeImage(recipe: KkiLogRecipe) {
        val list = _imageList.value.toMutableList()
        val listIterator = _imageList.value.listIterator()
        while (listIterator.hasNext()) {
            val tmp = listIterator.next()
            if (tmp.imageUrl == recipe.imageUrl)
                list.remove(tmp)
        }
        _imageList.value = list
    }

    fun getListCount() = _imageList.value.count() - 1

    fun isMaxCount() = getListCount() == 10

    fun upLoadKkiLog(title: String, description: String?, kick: String?) {
        viewModelScope.launch {
            val dto = UpLoadDto(_imageList.value.getImageUrl(), title, description, kick)
            upLoadKkiLogUseCase(dto)
        }
    }

    fun checkTitleEmpty(state: Boolean) {
        isValidFormat.update { isValidFormat.value.copy(isTitleEmpty = state) }
    }

    fun checkImageEmpty(state: Boolean) {
        isValidFormat.update { isValidFormat.value.copy(isImageEmpty = state) }
    }

    fun saveKkilog(title: String, description: String?, kick: String?) {
        savedKkilog.update {
            savedKkilog.value.copy(
                title = title,
                description = description,
                kick = kick
            )
        }
    }

    fun clearSaved() {
        savedKkilog.value = SavedKkilog()
    }
}