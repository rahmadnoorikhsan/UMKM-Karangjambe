package com.karangjambe.umkm.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karangjambe.umkm.domain.common.Result
import com.karangjambe.umkm.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        productRepository.getProducts().collect { result ->
            when (result) {
                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        statusMessage = result.message
                    )
                }

                is Result.Loading -> _state.update {
                    it.copy(
                        isLoading = true,
                        isError = false,
                        statusMessage = null
                    )
                }
                is Result.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        statusMessage = null,
                        products = result.data
                    )
                }
            }
        }
    }
}