package com.example.king_bob_nae.features.create.kkilog.presenter

import androidx.lifecycle.ViewModel
import com.example.king_bob_nae.features.create.kkilog.data.KkiLogRecipe
import com.example.king_bob_nae.features.create.kkilog.presenter.adapter.FIRST_HOLDER
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KkiLogViewModel : ViewModel() {

    private val _imageList =
        MutableStateFlow<List<KkiLogRecipe>>(listOf(KkiLogRecipe(FIRST_HOLDER)))
    val imageList = _imageList.asStateFlow()

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
}