package com.rwtest.android.api

import com.rwtest.android.model.CountryModel
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {
    @GET("v2/all")
    fun getCountryList(): Call<List<CountryModel>>
}