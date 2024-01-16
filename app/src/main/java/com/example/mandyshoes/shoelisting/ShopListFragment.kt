package com.example.mandyshoes.shoelisting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mandyshoes.R
import com.example.mandyshoes.databinding.FragmentShopListBinding
import com.example.mandyshoes.shoelisting.viewModel.SharedViewModel
import com.example.mandyshoes.shoelisting.viewModel.ShopListViewModel

class ShopListFragment : Fragment() {

    private lateinit var binding: FragmentShopListBinding
    private lateinit var viewModel: ShopListViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_list, container, false)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel = ShopListViewModel(sharedViewModel)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.shoeItem.observe(viewLifecycleOwner, Observer { imageRes ->
            navigateToDetails()
        })

    }

    private fun setUpViews(){
        binding.highHeels.setOnClickListener {
            viewModel.setArgument(1)
        }

        binding.tenis.setOnClickListener {
            viewModel.setArgument(2)
        }

        binding.greenTenis.setOnClickListener {
            viewModel.setArgument(3)
        }
    }

    private fun navigateToDetails() {

        val action = ShopListFragmentDirections.actionShopListFragmentToShopDetailFragment()
        findNavController().navigate(action)

    }
}