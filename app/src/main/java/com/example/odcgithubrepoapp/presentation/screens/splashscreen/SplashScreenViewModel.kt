package com.example.odcgithubrepoapp.presentation.screens.splashscreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samy.domain.usecase.IsFirstTimeUseCase
import com.samy.domain.usecase.SetFirstTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val isFirstTimeUseCase: com.samy.domain.usecase.IsFirstTimeUseCase,
    private val setFirstTimeUseCase: com.samy.domain.usecase.SetFirstTimeUseCase,
) : ViewModel() {

    private val _reposStateFlow = MutableStateFlow<Boolean?>(null)
    val reposStateFlow = _reposStateFlow.asStateFlow()

    init {
        checkIsFirstTime()
    }
    fun checkIsFirstTime() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isFirstTimeUseCase().collect { isFirstTime ->
                    _reposStateFlow.value = isFirstTime
                }
            } catch (e: Exception) {
                Log.e("SplashScreenViewModel", "Error fetching data: ${e.message}", e)
            }
        }
    }

    fun setFirstTime(isFirstTime: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setFirstTimeUseCase(isFirstTime)
                _reposStateFlow.value = isFirstTime
            } catch (e: Exception) {
                Log.e("SplashScreenViewModel", "Error setting data: ${e.message}", e)
            }
        }
    }
}