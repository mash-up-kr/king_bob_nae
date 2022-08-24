package com.example.king_bob_nae.features.create.detail.presentaion

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.detail.domain.ConvertDescriptionListUseCase
import com.example.king_bob_nae.features.create.detail.domain.ConvertImageListUseCase
import com.example.king_bob_nae.features.create.detail.domain.RequestDetailKkiLogUseCase
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogIngredient
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailKkiLogViewModel @Inject constructor(
    private val requestDetailKkiLogUseCase: RequestDetailKkiLogUseCase,
    private val convertDescriptionListUseCase: ConvertDescriptionListUseCase,
    private val convertImageListUseCase: ConvertImageListUseCase
) : ViewModel() {

    private val _kkiLogTitle = MutableStateFlow("") // 요리이름
    val kkiLogTitle = _kkiLogTitle.asStateFlow()

    private val _kkiLogImage = MutableStateFlow<Uri?>(null) // 요리이미지
    val kkiLogImage = _kkiLogImage.asStateFlow()

    private val _kkiLogIntroduce = MutableStateFlow("") // 한줄소개
    val kkiLogIntroduce = _kkiLogIntroduce.asStateFlow()

    private val _ingredientList = MutableStateFlow(emptyList<KkiLogIngredient>()) // 재료리스트
    val ingredientList = _ingredientList.asStateFlow()

    private val _recipeList = MutableStateFlow(emptyList<KkiLogRecipe>()) // 레시피리스트
    val recipeList = _recipeList.asStateFlow()

    private val _descriptionList = MutableStateFlow(emptyList<String>()) // 레시피 설명 리스트
    val descriptionList = _descriptionList.asStateFlow()

    private val _recipeImageList = MutableStateFlow(emptyList<Uri?>()) // 레시피 이미지 리스트
    val recipeImageList = _recipeImageList.asStateFlow()

    val emptyDescription: MutableStateFlow<String> = MutableStateFlow("")
    val emptyIngredient: MutableStateFlow<String> = MutableStateFlow("")

    var stepNum = 1

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

    fun setKkiLogTitle(title: String) {
        viewModelScope.launch {
            _kkiLogTitle.emit(title)
        }
    }

    fun setDescriptionLength(description: String) {
        viewModelScope.launch {
            _kkiLogIntroduce.emit(description)
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
        viewModelScope.launch {
            _kkiLogTitle.value
            _kkiLogImage.value
            _kkiLogIntroduce.value
            val ingredientList = _ingredientList.value.joinToString(",")
            val descriptionList = convertDescriptionListUseCase(recipeList.value)
            val imageList = convertImageListUseCase(recipeList.value)
        }
    }
}
