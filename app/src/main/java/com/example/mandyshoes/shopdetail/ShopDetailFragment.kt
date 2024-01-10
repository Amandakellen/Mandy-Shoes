package com.example.mandyshoes.shopdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mandyshoes.R
import com.example.mandyshoes.databinding.FragmentShopDetailBinding
import com.example.mandyshoes.shopdetail.viewModel.ShopDetailViewModel

class ShopDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopDetailBinding
    private lateinit var viewModel: ShopDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopDetailBinding.inflate(inflater, container, false)
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
        binding.shoesImage.setImageResource(requireArguments().getInt("imageResId"))
        binding.description.text = requireArguments().getString("description")
    }
}
