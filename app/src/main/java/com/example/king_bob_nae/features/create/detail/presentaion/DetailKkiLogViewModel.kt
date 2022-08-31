package com.example.king_bob_nae.features.create.detail.presentaion

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.detail.domain.ConvertDescriptionListUseCase
import com.example.king_bob_nae.features.create.detail.domain.ConvertImageListUseCase
import com.example.king_bob_nae.features.create.detail.domain.ConvertIngredientListUseCase
import com.example.king_bob_nae.features.create.detail.domain.RequestDetailKkiLogUseCase
import com.example.king_bob_nae.features.create.detail.domain.model.DetailKkiLogResult
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogIngredient
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.utils.NLog
import com.example.king_bob_nae.utils.stringToRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetailKkiLogViewModel @Inject constructor(
    private val requestDetailKkiLogUseCase: RequestDetailKkiLogUseCase,
    private val convertIngredientListUseCase: ConvertIngredientListUseCase,
    private val convertDescriptionListUseCase: ConvertDescriptionListUseCase,
    private val convertImageListUseCase: ConvertImageListUseCase
) : ViewModel() {

    private val _kkiLogTitle = MutableStateFlow("") // 요리이름
    val kkiLogTitle = _kkiLogTitle.asStateFlow()

    private val _kkiLogImage = MutableStateFlow<Uri?>(null) // 요리이미지
    val kkiLogImage = _kkiLogImage.asStateFlow()

    private val _kkiLogImageBody = MutableStateFlow<MultipartBody.Part?>(null) // 요리이미지
    val kkiLogImageBody = _kkiLogImageBody.asStateFlow()

    private val _kkiLogIntroduce = MutableStateFlow("") // 한줄소개
    val kkiLogIntroduce = _kkiLogIntroduce.asStateFlow()

    private val _ingredientList = MutableStateFlow(emptyList<KkiLogIngredient>()) // 재료리스트
    val ingredientList = _ingredientList.asStateFlow()

    private val _recipeList = MutableStateFlow(emptyList<KkiLogRecipe>()) // 레시피리스트
    val recipeList = _recipeList.asStateFlow()

    private val _descriptionList = MutableStateFlow(emptyList<String>()) // 레시피 설명 리스트
    val descriptionList = _descriptionList.asStateFlow()

    private val _detailKkiLogResult = MutableStateFlow(DetailKkiLogResult())
    val detailKkiLogResult = _detailKkiLogResult.asStateFlow()

    val emptyDescription: MutableStateFlow<String> = MutableStateFlow("")
    val emptyIngredient: MutableStateFlow<String> = MutableStateFlow("")

    var stepNum = 1
    var ingredientNum = 1

    private val _recipeItem = MutableStateFlow(KkiLogRecipe())

    private val _isEditMode: MutableSharedFlow<Boolean> =
        MutableSharedFlow()
    val isEditMode = _isEditMode.asSharedFlow()

    private val _setKkiLogImageEvent: MutableSharedFlow<Unit> =
        MutableSharedFlow()
    val setKkiLogImageEvent = _setKkiLogImageEvent.asSharedFlow()

    private val _showToastMessage: MutableSharedFlow<String> =
        MutableSharedFlow()
    val showToastMessage = _showToastMessage.asSharedFlow()

    private val _setRecipeDescriptionText: MutableSharedFlow<String> =
        MutableSharedFlow()
    val setRecipeDescriptionText = _setRecipeDescriptionText.asSharedFlow()

    init {
        addView()
    }

    fun setEmptyDescription(text: String) {
        emptyDescription.value = text
    }

    fun setEmptyIngredient(text: String) {
        emptyIngredient.value = text
    }

    private fun addView() {
        viewModelScope.launch {
            _ingredientList.emit(listOf(KkiLogIngredient(ingredientNum, "")))
            _recipeList.emit(listOf(KkiLogRecipe(stepNum, "", null, null, false)))
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

    fun addRecipe() {
        viewModelScope.launch {
            _recipeList.emit(
                _recipeList.value.toMutableList() + KkiLogRecipe(++stepNum, "", null)
            )
        }
    }

    fun addIngredient() {
        viewModelScope.launch {
            _ingredientList.emit(
                _ingredientList.value.toMutableList() + KkiLogIngredient(++ingredientNum, "")
            )
        }
    }

    fun addRecipeImage(imageUri: Uri?, recipeImageBody: MultipartBody.Part?) {
        viewModelScope.launch {
            _recipeList.update {
                _recipeList.value.map {
                    if (it.stepNumber == _recipeItem.value.stepNumber) {
                        it.copy(
                            imageUri = imageUri,
                            description = emptyDescription.value,
                            imageBody = recipeImageBody
                        )
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

    fun setImage(imageUri: Uri?, body: MultipartBody.Part?, body2: MultipartBody.Part?) {
        if (_kkiLogImage.value == null) {
            _kkiLogImage.value = imageUri
            _kkiLogImageBody.value = body2
        } else {
            addRecipeImage(imageUri, body)
        }
    }

    fun updateRecipeDescription(item: KkiLogRecipe, description: String) {
        viewModelScope.launch {
            emptyDescription.value = description
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
    }

    fun updateIngredient(item: KkiLogIngredient, ingredient: String) {
        viewModelScope.launch {
            emptyIngredient.value = ingredient
            _ingredientList.update {
                _ingredientList.value.map {
                    if (it.num == item.num) {
                        it.copy(ingredient = emptyIngredient.value)
                    } else {
                        it
                    }
                }
            }
        }
    }

    fun requestDetailKkiLog() {
        viewModelScope.launch {
            if (_kkiLogTitle.value.isEmpty() || _kkiLogImageBody.value == null || _kkiLogIntroduce.value.isEmpty() || _ingredientList.value.isEmpty() || _recipeList.value.isEmpty()) {
                _showToastMessage.emit("입력되지 않은 곳을 확인해주세요.")
            } else {
                val title = _kkiLogTitle.value
                val kkiLogImage = _kkiLogImageBody.value
                val introduce = _kkiLogIntroduce.value
                val ingredientList = convertIngredientListUseCase(_ingredientList.value)
                val descriptionList = convertDescriptionListUseCase(recipeList.value)
                val recipeImageList = convertImageListUseCase(recipeList.value)

                if (kkiLogImage != null) {
                    requestDetailKkiLogUseCase(
                        kkiLogImage,
                        recipeImageList,
                        descriptionList.stringToRequestBody(),
                        title.stringToRequestBody(),
                        introduce.stringToRequestBody(),
                        ingredientList.stringToRequestBody()
                    ).catch { e ->
                        NLog.d("kelly", e.message.toString())
                        _showToastMessage.emit("작성 실패")
                    }.collect {
                        _detailKkiLogResult.emit(it)
                        _showToastMessage.emit("작성 완료!")
                    }
                }
            }
//            NLog.d("kelly", "title = $title image = $kkiLogImage 한줄소개 = $introduce")
//            NLog.d("kelly 레시피 리스트", recipeList.value.toString())
//            NLog.d("kelly 재료 리스트", ingredientList)
//            NLog.d("kelly 설명 리스트", descriptionList)
//            NLog.d("kelly 이미지 리스트", recipeImageList.toString())
        }
    }

    fun updateRecipeList(recipeList: List<KkiLogRecipe>) {
        viewModelScope.launch {
            _recipeList.value = recipeList
        }
    }

    fun onEditClick() {
        viewModelScope.launch {
            _isEditMode.emit(_recipeList.value.first().isEditable)
        }
    }

    fun deleteRecipe(item: KkiLogRecipe) {
        val list = _recipeList.value.toMutableList()
        if (list.size == 1) {
            deleteRecipeItem(item)
            stepNum = 1
            addRecipe()
        } else {
            deleteRecipeItem(item)
        }
    }

    fun deleteRecipeItem(item: KkiLogRecipe) {
        val list = _recipeList.value.toMutableList()
        _recipeList.value.map {
            if (it == item) {
                list.remove(it)
            }
        }
        _recipeList.value = list
    }

    fun changeEditMode() {
        _recipeList.update {
            _recipeList.value.map {
                it.copy(isEditable = !it.isEditable)
            }
        }
    }

    fun setKkiLogImage() {
        viewModelScope.launch {
            _setKkiLogImageEvent.emit(Unit)
        }
    }
}
