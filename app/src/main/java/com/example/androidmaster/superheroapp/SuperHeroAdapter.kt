package com.example.androidmaster.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {
    fun updateList(superHeroList: List<SuperHeroItemResponse>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SuperHeroViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_superhero, parent, false
                )
        )

    override fun getItemCount() = superHeroList.size

    override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {
        viewholder.bind(superHeroList[position], onItemSelected)
    }
}