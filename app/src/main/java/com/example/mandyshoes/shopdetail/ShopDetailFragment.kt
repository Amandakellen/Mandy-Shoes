package com.example.mandyshoes.shopdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mandyshoes.R
import com.example.mandyshoes.databinding.FragmentShopDetailBinding

class ShopDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding.shoesImage.setImageResource(requireArguments().getInt("imageResId"))
        binding.description.text = requireArguments().getString("description")
    }
}
