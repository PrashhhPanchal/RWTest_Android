package com.rwtest.android.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rwtest.android.Listener.ItemClickListener
import com.rwtest.android.R
import com.rwtest.android.adapters.CountryAdapter
import com.rwtest.android.api.ApiResponseListener
import com.rwtest.android.model.CountryModel
import com.rwtest.android.viewmodel.CountryVM


class CountryActivity : AppCompatActivity(R.layout.activity_country), ApiResponseListener, ItemClickListener {

    private lateinit var viewModel: CountryVM
    var adapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CountryVM::class.java]
        viewModel.getCountryData(this)

        val edtSearchCountry = findViewById<AppCompatEditText>(R.id.edtSearchCountry)
        edtSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                adapter!!.filter(s.toString())
            }
        })
    }

    override fun onSuccess(countryList: List<CountryModel>) {
        val recyclerview = findViewById<RecyclerView>(R.id.rv_country)
        adapter = CountryAdapter(viewModel.countryList, this)
        recyclerview.adapter = adapter
    }

    override fun OnItemClick(countryModel: CountryModel) {
        viewModel.selectedCountry = countryModel
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.putExtra("Country", Gson().toJson(countryModel))
        startActivity(intent)
    }
}