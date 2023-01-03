package com.example.dttproject.model

// to handle the states of the application
sealed class ViewState {
    object Empty: ViewState()
    object Loading : ViewState()
    data class Success(val data: List<HouseItem>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()

}
