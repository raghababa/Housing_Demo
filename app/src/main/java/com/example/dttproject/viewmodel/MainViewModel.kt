package com.example.dttproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.example.dttproject.model.HouseItem
import com.example.dttproject.model.ApiService
import com.example.dttproject.model.DetailViewState
import com.example.dttproject.model.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



class MainViewModel : ViewModel() {
    //    to notify changes at run time
    private var houseListResponse: List<HouseItem> by mutableStateOf(listOf())
    private val _viewState =
        MutableStateFlow<ViewState>(ViewState.Loading)    //loading is always the default state
    val houses = _viewState.asStateFlow()
    private val _detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)
    val houseDetails = _detailViewState.asStateFlow()

    //    call the apiService and get all houses
    fun getAllHouseList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val houseList = apiService.getHouses()
                houseListResponse = houseList
            } catch (e: java.lang.Exception) {
                _viewState.value = ViewState.Error(e)
            }

        }
    }
    // sort the list by price
    fun sortAllHouses() {
        val sorted = houseListResponse.sortedWith(compareBy { it.price })
        _viewState.value = ViewState.Success(sorted)
    }

    //get the house by Id
    fun getHouseById(isIdNo: String) {
        val descriptionById = houseListResponse.first { (it.id).toString() == isIdNo }
        _detailViewState.value = DetailViewState.Success(descriptionById)

    }

}