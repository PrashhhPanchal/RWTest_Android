package com.rwtest.android.api

import com.rwtest.android.model.CountryModel

interface ApiResponseListener {

    fun onSuccess(countryList: List<CountryModel>)
}