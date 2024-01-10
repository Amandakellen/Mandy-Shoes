package com.example.mandyshoes.shoelisting.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mandyshoes.R
import com.example.mandyshoes.shoelisting.Descriptions

class ShopListViewModel : ViewModel() {

    var imageResLiveData = MutableLiveData<Int>()
    var descriptionLiveData = MutableLiveData<String>()

    fun setArgument(index: Int) {
        when (index) {
            1 -> {
                imageResLiveData.value = R.drawable.high_heels
                descriptionLiveData.value = Descriptions.DESCRIPTION_HIGH_HEELS
            }
            2 -> {
                imageResLiveData.value = R.mipmap.tenis_foreground
                descriptionLiveData.value = Descriptions.DESCRIPTION_TENIS
            }
            else -> {
                imageResLiveData.value = R.mipmap.green_tenis_foreground
                descriptionLiveData.value = Descriptions.DESCRIPTION_GREEN_TENIS
            }
        }

    }
}
