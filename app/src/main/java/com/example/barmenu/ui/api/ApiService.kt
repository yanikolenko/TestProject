package com.example.barmenu.ui.api

import com.example.barmenu.BuildConfig
import com.example.barmenu.ui.model.CoctailsDvo
import com.example.barmenu.ui.model.DrinksAllInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET(BuildConfig.END_URL_API_ALCO)
    fun getAlcoholDrink(): Call<CoctailsDvo>

    @GET(BuildConfig.END_URL_API_NONALCO)
    fun getNonAlcoholDrink(): Call<CoctailsDvo>

    @GET
    fun getAllInfoById(@Url url: String?): Call<DrinksAllInfo>
}