package com.emirhan.yemekbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirhan.yemekbook.adapter.recAdapter
import com.emirhan.yemekbook.databinding.FragmentFoodListBinding
import com.emirhan.yemekbook.viewModel.foodListViewModel


class foodList : Fragment() {
    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:foodListViewModel
    private val adapter=recAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[foodListViewModel::class.java]
        viewModel.refreshData()

        binding.foodRec.layoutManager=LinearLayoutManager(requireContext())
        binding.foodRec.adapter=adapter

        binding.swipe.setOnRefreshListener {
            binding.foodRec.visibility=View.GONE
            binding.foodErrorMessage.visibility=View.GONE
            binding.FoodprogressBar.visibility=View.VISIBLE
            viewModel.refreshDataInternet()
            binding.swipe.isRefreshing=false
            Toast.makeText(requireContext(),"Yükleniyor",Toast.LENGTH_SHORT).show()
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner){
            adapter.besinListesiniGuncelle(it)
            binding.foodRec.visibility=View.VISIBLE
        }
        viewModel.foodErrorMessage.observe(viewLifecycleOwner){
            if (it){
                binding.foodErrorMessage.visibility=View.VISIBLE
                binding.foodRec.visibility=View.GONE
            }else{
                binding.foodErrorMessage.visibility=View.GONE
            }
        }
        viewModel.foodLoading.observe(viewLifecycleOwner){
            if(it){
                binding.foodErrorMessage.visibility=View.GONE
                binding.foodRec.visibility=View.GONE
                binding.FoodprogressBar.visibility=View.VISIBLE
            }else{
                binding.FoodprogressBar.visibility=View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}