package com.example.mandyshoes.shopdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mandyshoes.R
import com.example.mandyshoes.data.ShoeItem
import com.example.mandyshoes.databinding.FragmentShopDetailBinding
import com.example.mandyshoes.shoelisting.viewModel.SharedViewModel
import com.example.mandyshoes.shopdetail.viewModel.ShopDetailViewModel

class ShopDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopDetailBinding
    private lateinit var viewModel: ShopDetailViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopDetailBinding.inflate(inflater, container, false)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ShopDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setUpViews()
    }
    private fun observeViewModel() {
        viewModel.selectedItem.observe(viewLifecycleOwner) { selectedItem ->
            if(selectedItem != "No item purchased yet") binding.description.text = selectedItem
        }

    }
    private fun setUpViews() {
        val item = ShoeItem(
            sharedViewModel.selectedItem.value?.imageResId ?: R.drawable.high_heels,
            sharedViewModel.selectedItem.value?.description ?: "")

        binding.data = item
    }
}
