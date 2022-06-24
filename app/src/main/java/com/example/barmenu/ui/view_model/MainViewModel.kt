package com.example.barmenu.ui.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barmenu.BuildConfig
import com.example.barmenu.ui.api.ApiService
import com.example.barmenu.ui.model.CoctailsDvo
import com.example.barmenu.ui.model.Drink
import com.example.barmenu.ui.model.DrinkItem
import com.example.barmenu.ui.model.DrinksAllInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    val allAlcoholCocktails = MutableLiveData<List<DrinkItem?>?>()
    val allNonAlcoholCocktails = MutableLiveData<List<DrinkItem?>?>()
    val allDrinksInfo = MutableLiveData<List<Drink?>?>()

    val retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL_API)
        .build()
        .create(ApiService::class.java)

    init {
        getAlcoholCocktailsData()
        getNonAlcoholCocktails()
        getDrinksById("")
    }

    private fun getAlcoholCocktailsData(){
        val retrofitData = retrofit.getAlcoholDrink()
        retrofitData.enqueue(object : retrofit2.Callback<CoctailsDvo>{
            override fun onResponse(call: Call<CoctailsDvo>, response: Response<CoctailsDvo>) {
                val body = response.body()?.drinks
                allAlcoholCocktails.postValue(body)
            }

            override fun onFailure(call: Call<CoctailsDvo>, t: Throwable) {
                Log.d("trouble", "ProblemWithApi")
            }
        })
    }

    private fun getNonAlcoholCocktails(){
        val retrofitData = retrofit.getNonAlcoholDrink()
        retrofitData.enqueue(object : retrofit2.Callback<CoctailsDvo>{
            override fun onResponse(call: Call<CoctailsDvo>, response: Response<CoctailsDvo>) {
                val body = response.body()?.drinks
                allNonAlcoholCocktails.postValue(body)
            }

            override fun onFailure(call: Call<CoctailsDvo>, t: Throwable) {
                Log.d("trouble", "ProblemWithApi")
            }
        })
    }

    fun getDrinksById(link: String){
        val retrofitData = retrofit.getAllInfoById(link)
        retrofitData.enqueue(object : retrofit2.Callback<DrinksAllInfo>{
            override fun onResponse(call: Call<DrinksAllInfo>, response: Response<DrinksAllInfo>) {
                val body = response.body()?.drinks
                allDrinksInfo.postValue(body)
            }

            override fun onFailure(call: Call<DrinksAllInfo>, t: Throwable) {
                Log.d("trouble", "ProblemWithApi")
            }


        })
    }
}