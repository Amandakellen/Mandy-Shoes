package com.example.mandyshoes.shopdetail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopDetailViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = _selectedItem

    init {
        _selectedItem.value = "No item purchased yet"
    }

    fun onBuyButtonClick() {
        _selectedItem.value = "Item purchased!"
    }
}
