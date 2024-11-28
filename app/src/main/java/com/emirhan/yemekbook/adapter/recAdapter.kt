package com.emirhan.yemekbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.yemekbook.databinding.RecRowBinding
import com.emirhan.yemekbook.model.foodData
import com.emirhan.yemekbook.util.gorselIndir
import com.emirhan.yemekbook.util.placeholderYap
import com.emirhan.yemekbook.view.foodListDirections

class recAdapter(val besinListesi:ArrayList<foodData>) :RecyclerView.Adapter<recAdapter.recViewHolder>(){
    class recViewHolder(val bag:RecRowBinding):RecyclerView.ViewHolder(bag.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recViewHolder {
        val binding=RecRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return recViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }
    fun besinListesiniGuncelle(newList:List<foodData>){
        besinListesi.clear()
        besinListesi.addAll(newList)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: recViewHolder, position: Int) {
        holder.bag.textRec1.text=besinListesi[position].isim
        holder.bag.textRec2.text=besinListesi[position].kalori

        holder.itemView.setOnClickListener {
            val action=foodListDirections.actionFoodListToFoodDetailFragment(besinListesi[position].id)
            Navigation.findNavController(it).navigate(action)
        }
        holder.bag.recMage.gorselIndir(besinListesi[position].gorsel, placeholderYap(holder.itemView.context))

    }
}