package com.karangjambe.umkm.presentation.screen.home

sealed interface HomeEvent {
    data class OnSearch(
        val query: String
    ) : HomeEvent
    data class OnQueryChange(
        val query: String,
    ) : HomeEvent
}