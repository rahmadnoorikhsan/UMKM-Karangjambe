package com.karangjambe.umkm.presentation.screen.detail

sealed interface DetailEvent {
    data class OnGetDetailProduct(
        val id: Int
    ) : DetailEvent
}