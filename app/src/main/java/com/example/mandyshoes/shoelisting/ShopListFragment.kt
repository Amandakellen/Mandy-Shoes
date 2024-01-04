package com.example.mandyshoes.shoelisting

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.mandyshoes.R
import com.example.mandyshoes.databinding.FragmentShopListBinding

class ShopListFragment : Fragment() {

    private lateinit var binding: FragmentShopListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShopListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }
    private fun setUpViews(){
        binding.greenTenis.setOnClickListener{
            setArgument(R.mipmap.green_tenis_foreground)
        }

        binding.highHeels.setOnClickListener{
            setArgument(R.drawable.high_heels)

        }
        binding.tenis.setOnClickListener{
            setArgument(R.mipmap.tenis_foreground)
        }
    }

    private fun setArgument(imageRes: Int){
        val action = ShopListFragmentDirections.actionShopListFragmentToShopDetailFragment(imageRes)
        findNavController().navigate(action)
    }
}