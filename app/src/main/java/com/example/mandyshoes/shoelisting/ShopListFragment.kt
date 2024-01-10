package com.example.mandyshoes.shoelisting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mandyshoes.R
import com.example.mandyshoes.databinding.FragmentShopListBinding
import com.example.mandyshoes.shoelisting.viewModel.ShopListViewModel

class ShopListFragment : Fragment() {

    private lateinit var binding: FragmentShopListBinding
    private lateinit var viewModel: ShopListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_list, container, false)
        viewModel = ShopListViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.imageResLiveData.observe(viewLifecycleOwner, Observer { imageRes ->
            navigateToDetails()
        })

        viewModel.descriptionLiveData.observe(viewLifecycleOwner, Observer { description ->
            navigateToDetails()
        })
    }

    private fun navigateToDetails() {
        val imageRes = viewModel.imageResLiveData.value
        val description = viewModel.descriptionLiveData.value

        if (imageRes != null && description != null) {
            val action = ShopListFragmentDirections.actionShopListFragmentToShopDetailFragment(
                imageRes,
                description
            )
            findNavController().navigate(action)
        } else {
            Log.e("ShopListFragment", "Valores de imageRes ou description são nulos.")
        }
    }
}
