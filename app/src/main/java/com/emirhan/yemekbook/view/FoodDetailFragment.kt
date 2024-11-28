package com.emirhan.yemekbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.emirhan.yemekbook.databinding.FragmentFoodDetailBinding
import com.emirhan.yemekbook.util.gorselIndir
import com.emirhan.yemekbook.util.placeholderYap
import com.emirhan.yemekbook.viewModel.foodDetailViewModel


class foodDetailFragment : Fragment() {
    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:foodDetailViewModel
    private var foodId=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[foodDetailViewModel::class.java]
        arguments?.let {
            foodId=foodDetailFragmentArgs.fromBundle(it).foodID
        }
        viewModel.roomVerisiniAl(foodId)
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.foodLiveDAta.observe(viewLifecycleOwner){
            binding.foodNameText.text=it.isim
            binding.foodProteinText.text=it.protein
            binding.foodCaloritext.text=it.kalori
            binding.foodOilText.text=it.yag
            binding.foodCarbohydrateText.text=it.karbonhidrat
            binding.foodDetailMage.gorselIndir(it.gorsel, placeholderYap(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}