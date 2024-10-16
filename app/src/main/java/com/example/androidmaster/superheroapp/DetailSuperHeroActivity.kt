package com.example.androidmaster.superheroapp

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.example.androidmaster.superheroapp.SuperHeroListActivity.Companion.EXTRA_HERO_ID
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperHeroBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retrofit = getRetrofit()

        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val heroId = intent.getStringExtra(EXTRA_HERO_ID).orEmpty()

        getSuperHeroDetails(heroId)
    }

    private fun getSuperHeroDetails(heroId: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val heroDetails =
                retrofit.create(ApiService::class.java).getSuperheroDetails(heroId)

            if (heroDetails.body() != null) {
                runOnUiThread {
                    createUI( heroDetails.body()!! )
                }
            }
        }
    }

    private fun createUI(superhero: SuperheroDetailsResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)

        binding.tvHeroName.text = superhero.name
        binding.tvHeroIdentiy.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher

        setStatsHeight(superhero.powerstats)
    }

    private fun setStatsHeight(powerstats: PowerStatsResponse) {
        updateHeight(binding.vIntelligence, powerstats.intelligence)
        updateHeight(binding.vStrength, powerstats.strength)
        updateHeight(binding.vPower, powerstats.power)
        updateHeight(binding.vCombat, powerstats.combat)
        updateHeight(binding.vDurability, powerstats.durability)
        updateHeight(binding.vSpeed, powerstats.speed)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}