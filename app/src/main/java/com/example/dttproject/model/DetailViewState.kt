package com.example.dttproject.model

sealed class DetailViewState {
    object Empty: DetailViewState()
    object Loading : DetailViewState()
    data class Success(val data: HouseItem) : DetailViewState()
    data class Error(val exception: Throwable) : DetailViewState()
}