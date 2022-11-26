package com.rwtest.android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rwtest.android.api.APIClient
import com.rwtest.android.api.APIInterface
import com.rwtest.android.api.ApiResponseListener
import com.rwtest.android.model.CountryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CountryVM: ViewModel() {

    var apiInterface: APIInterface? = null
    var countryList = arrayListOf<CountryModel>()
    var selectedCountry : CountryModel? = null

    init {
        apiInterface = APIClient.client!!.create(APIInterface::class.java)
    }

    fun getCountryData(listener: ApiResponseListener) {
        /**
         GET Country List
        **/
        val call: Call<List<CountryModel>> = apiInterface!!.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(call: Call<List<CountryModel>>, response: Response<List<CountryModel>>) {
                Log.d("TAG", response.code().toString() + "")
                val resource: List<CountryModel> = response.body()!!
                countryList = resource as ArrayList<CountryModel>
                listener.onSuccess(countryList)
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable?) {
                call.cancel()
            }
        })
    }
}