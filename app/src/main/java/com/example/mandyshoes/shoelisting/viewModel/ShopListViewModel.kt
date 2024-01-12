package com.example.mandyshoes.shoelisting.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mandyshoes.R
import com.example.mandyshoes.data.ShoeItem
import com.example.mandyshoes.shoelisting.Descriptions

class ShopListViewModel(private val sharedViewModel: SharedViewModel) : ViewModel() {

    var _shoeItem = MutableLiveData<ShoeItem>()
    val shoeItem: LiveData<ShoeItem> = _shoeItem

    fun setArgument(index: Int) {
        _shoeItem.value = when (index) {
            1 -> {
                ShoeItem(R.drawable.high_heels, Descriptions.DESCRIPTION_HIGH_HEELS)
            }
            2 -> ShoeItem(R.mipmap.tenis_foreground, Descriptions.DESCRIPTION_TENIS)
            else -> ShoeItem(R.mipmap.green_tenis_foreground, Descriptions.DESCRIPTION_GREEN_TENIS)
        }

        sharedViewModel.updateSelectedItem(_shoeItem.value)

    }
}
