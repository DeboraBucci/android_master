package com.example.androidmaster.superheroapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmaster.databinding.ActivitySuperHeroListBinding
import com.example.androidmaster.todoapp.ApiService
import com.example.androidmaster.todoapp.SuperHeroAdapter
import com.example.androidmaster.todoapp.SuperHeroDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()

        initUI()
    }

    private fun initUI() {
        binding.svSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())

                return false
            }

            override fun onQueryTextChange(newText: String?) = false

        })

        adapter = SuperHeroAdapter()
        binding.rvHeroes.setHasFixedSize(true)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.pbLoadingHeroes.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit
                    .create(ApiService::class.java)
                    .getSuperheroes(query)

            if (myResponse.isSuccessful) {
                val response: SuperHeroDataResponse? = myResponse.body()

                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.results)
                    }
                }
            } else {

            }

            runOnUiThread {
                binding.pbLoadingHeroes.isVisible = false
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}