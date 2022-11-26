package com.rwtest.android.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rwtest.android.R
import com.rwtest.android.model.CountryModel
import com.rwtest.android.viewmodel.CountryVM

class CountryDetailActivity : AppCompatActivity(R.layout.activity_country_detail) {

    private lateinit var viewModel: CountryVM

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CountryVM::class.java]

        viewModel.selectedCountry = Gson().fromJson(intent.extras!!.getString("Country"), CountryModel::class.java)

        val imgCountry = findViewById<AppCompatImageView>(R.id.imgCountry)
        val tvCountryName = findViewById<AppCompatTextView>(R.id.tvCountryName)
        val tvCapital = findViewById<AppCompatTextView>(R.id.tvCapital)
        val tvRegion = findViewById<AppCompatTextView>(R.id.tvRegion)
        val tvCode = findViewById<AppCompatTextView>(R.id.tvCode)

        Glide.with(this).load(viewModel.selectedCountry!!.flags.png).into(imgCountry)
        tvCountryName.text = viewModel.selectedCountry!!.name
        tvCapital.text = viewModel.selectedCountry!!.capital
        tvRegion.text = viewModel.selectedCountry!!.region
        tvCode.text = "Country Code: " + viewModel.selectedCountry!!.alpha3Code

    }
}