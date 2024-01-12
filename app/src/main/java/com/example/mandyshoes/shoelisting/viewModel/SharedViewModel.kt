package com.example.mandyshoes.shoelisting.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mandyshoes.data.ShoeItem

class SharedViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<ShoeItem?>()
    val selectedItem: LiveData<ShoeItem?> = _selectedItem

    fun updateSelectedItem(item: ShoeItem?) {
        _selectedItem.value = item
    }
}