package com.example.king_bob_nae.features.create.detail.presentaion

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.detail.domain.KkiLogIngredient
import com.example.king_bob_nae.features.create.detail.domain.KkiLogRecipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailKkiLogViewModel @Inject constructor() : ViewModel() {
    private val _kkiLogTitle = MutableStateFlow("")
    val kkiLogTitle = _kkiLogTitle.asStateFlow()

    private val _kkiLogIntroduce = MutableStateFlow("")
    val kkiLogIntroduce = _kkiLogIntroduce.asStateFlow()

    private val _ingredientList = MutableStateFlow(emptyList<KkiLogIngredient>())
    val ingredientList = _ingredientList.asStateFlow()

    private val _descriptionList = MutableStateFlow(emptyList<String>())
    val descriptionList = _descriptionList.asStateFlow()

    private val _recipeImageList = MutableStateFlow(emptyList<Uri?>())
    val recipeImageList = _recipeImageList.asStateFlow()

    private val _kkiLogImage = MutableStateFlow<Uri?>(null)
    val kkiLogImage = _kkiLogImage.asStateFlow()

    private val _recipeList = MutableStateFlow(emptyList<KkiLogRecipe>())
    val recipeList = _recipeList.asStateFlow()

    val emptyDescription: MutableStateFlow<String> = MutableStateFlow("")
    val emptyIngredient: MutableStateFlow<String> = MutableStateFlow("")

    var stepNum = 1

    var targetItem = MutableStateFlow(KkiLogRecipe())

    private val _recipeItem = MutableStateFlow(KkiLogRecipe())

    init {
        addView()
    }

    private fun addView() {
        viewModelScope.launch {
            _ingredientList.emit(listOf(KkiLogIngredient(0, "")))
            _recipeList.emit(listOf(KkiLogRecipe(stepNum, "", null)))
        }
    }

    fun addIngredient() {
        viewModelScope.launch {
            _ingredientList.emit(
                _ingredientList.value.toMutableList() + KkiLogIngredient(
                    _ingredientList.value.size - 1,
                    ""
                )
            )
        }
    }

    fun addRecipe() {
        viewModelScope.launch {
            _recipeList.emit(
                _recipeList.value.toMutableList() + KkiLogRecipe(++stepNum, "", null)
            )
        }
    }

    fun addRecipeImage(imageUri: Uri?) {
        viewModelScope.launch {
            _recipeList.update {
                _recipeList.value.map {
                    if (it.stepNumber == _recipeItem.value.stepNumber) {
                        it.copy(imageUri = imageUri)
                    } else {
                        it
                    }
                }
            }
        }
    }

    fun setRecipeItem(item: KkiLogRecipe) {
        _recipeItem.value = item
    }

    fun setKkiLogImage(imageUri: Uri?) {
        _kkiLogImage.value = imageUri
    }

    fun updateRecipeDescription(item: KkiLogRecipe) {
        _recipeList.update {
            _recipeList.value.map {
                if (it.stepNumber == item.stepNumber) {
                    it.copy(description = emptyDescription.value)
                } else {
                    it
                }
            }
        }
    }

    fun updateIngredient(item: KkiLogIngredient) {
        _ingredientList.update {
            _ingredientList.value.map {
                if (it.position == item.position) {
                    it.copy(ingredient = emptyIngredient.value)
                } else {
                    it
                }
            }
        }
    }

    fun requestDetailKkiLog() {
    }
}
