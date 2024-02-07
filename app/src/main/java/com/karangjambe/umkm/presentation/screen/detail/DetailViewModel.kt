package com.karangjambe.umkm.presentation.screen.detail

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
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnGetDetailProduct -> getDetailProduct(event.id)
        }
    }

    private fun getDetailProduct(id: Int) = viewModelScope.launch {
        repository.getProductById(id).collect { result ->
            when (result) {
                is Result.Error -> _state.update {
                    it.copy(
                        isError = true,
                        isLoading = false,
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
                        detailProduct = result.data
                    )
                }
            }
        }
    }
}