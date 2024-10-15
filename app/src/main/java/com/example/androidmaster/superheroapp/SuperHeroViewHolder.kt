package com.example.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse) {
        binding.tvSuperheroName.text = superHeroItemResponse.name
    }
}