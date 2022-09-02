package com.example.king_bob_nae.features.create.detail.presentaion

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.detail.domain.*
import com.example.king_bob_nae.features.create.detail.domain.model.DetailKkiLogResult
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogIngredient
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import com.example.king_bob_nae.utils.NLog
import com.example.king_bob_nae.utils.stringToRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetailKkiLogViewModel @Inject constructor(
    private val requestDetailKkiLogUseCase: RequestDetailKkiLogUseCase,
    private val convertIngredientListUseCase: ConvertIngredientListUseCase,
    private val convertDescriptionListUseCase: ConvertDescriptionListUseCase,
    private val convertImageListUseCase: ConvertImageListUseCase,
    private val sortByStepNumRecipeListUseCase: SortByStepNumRecipeListUseCase
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

    private val _isEditMode: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isEditMode = _isEditMode.asStateFlow()

    private val _setKkiLogImageEvent: MutableSharedFlow<Unit> =
        MutableSharedFlow()
    val setKkiLogImageEvent = _setKkiLogImageEvent.asSharedFlow()

    private val _showToastMessage: MutableSharedFlow<String> =
        MutableSharedFlow()
    val showToastMessage = _showToastMessage.asSharedFlow()

    private val _setRecipeDescriptionText: MutableSharedFlow<String> =
        MutableSharedFlow()
    val setRecipeDescriptionText = _setRecipeDescriptionText.asSharedFlow()

    var kkiLogImagePosition = ""

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
            _recipeList.emit(listOf(KkiLogRecipe(stepNum, "", null, null, _isEditMode.value)))
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
                _recipeList.value.toMutableList() + KkiLogRecipe(
                    ++stepNum,
                    "",
                    null,
                    null,
                    _isEditMode.value
                )
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

    fun setRecipeImage(imageUri: Uri?, recipeImageBody: MultipartBody.Part?) {
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

    fun setKKiLogImagePosition(type: String) {
        kkiLogImagePosition = type
    }

    fun setRecipeItem(item: KkiLogRecipe) {
        _recipeItem.value = item
    }

    fun setBrandImage(imageUri: Uri?, body: MultipartBody.Part?) {
        _kkiLogImage.value = imageUri
        _kkiLogImageBody.value = body
    }

    private val updateIngredientsJob = hashMapOf<Int, Job>()
    fun updateIngredient(item: KkiLogIngredient, ingredient: String) {
        updateIngredientsJob[item.num]?.cancel()
        viewModelScope.launch {
            delay(500)
            _ingredientList.update {
                _ingredientList.value.map {
                    if (it.num == item.num) {
                        it.copy(ingredient = ingredient)
                    } else {
                        it
                    }
                }
            }
        }.also {
            updateIngredientsJob[item.num] = it
        }
    }

    private val updateRecipeJob = hashMapOf<Int, Job>()
    fun updateRecipeDescription(item: KkiLogRecipe, description: String) {
        updateRecipeJob[item.stepNumber]?.cancel()
        viewModelScope.launch {
            delay(500)
            _recipeList.update {
                emptyDescription.value = description
                _recipeList.value.map {
                    if (it.stepNumber == item.stepNumber) {
                        it.copy(description = emptyDescription.value)
                    } else {
                        it
                    }
                }
            }
        }.also {
            NLog.d("kelly recipeList", _recipeList.value.toString())
            updateRecipeJob[item.stepNumber] = it
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
        _recipeList.value = recipeList
        viewModelScope.launch {
            delay(500)
            orderRecipeList()
        }
    }

    fun orderRecipeList() {
        viewModelScope.launch {
            sortByStepNumRecipeListUseCase(_recipeList.value).collect {
                _recipeList.value = it
            }
        }
    }

    fun deleteRecipe(item: KkiLogRecipe) {
        val list = _recipeList.value.toMutableList()
        if (list.size == 1) {
            deleteRecipeItem(item)
            addRecipe()
            changeEditMode()
        } else {
            deleteRecipeItem(item)
        }
    }

    fun deleteRecipeItem(item: KkiLogRecipe) {
        val list = _recipeList.value.toMutableList()
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            if (iterator.next() == item) {
                iterator.remove()
            }
        }
        --stepNum
        _recipeList.value = list

        var num = 1
        _recipeList.update {
            _recipeList.value.map {
                it.copy(stepNumber = num++)
            }
        }
    }

    fun changeEditMode() {
        viewModelScope.launch {
            _recipeList.update {
                _recipeList.value.map {
                    it.copy(isEditable = !it.isEditable)
                }
            }
            _isEditMode.emit(!_isEditMode.value)
        }
    }

    fun setKkiLogImage() {
        viewModelScope.launch {
            _setKkiLogImageEvent.emit(Unit)
            kkiLogImagePosition = "brand"
        }
    }
}
