package com.rwtest.android.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rwtest.android.Listener.ItemClickListener
import com.rwtest.android.R
import com.rwtest.android.model.CountryModel

class CountryAdapter(private var mList: List<CountryModel>, var listener: ItemClickListener) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    var primaryData = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        Glide.with(holder.imgCountry).load(ItemsViewModel.flags.png).into(holder.imgCountry);
        holder.tvCountryName.text = ItemsViewModel.name

        holder.lytCountry.setOnClickListener {
            listener.OnItemClick(ItemsViewModel)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imgCountry: AppCompatImageView = itemView.findViewById(R.id.imgCountry)
        val tvCountryName: AppCompatTextView = itemView.findViewById(R.id.tvCountryName)
        val lytCountry: LinearLayoutCompat = itemView.findViewById(R.id.lytCountry)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(text: String) {
        mList = primaryData
        val temp: MutableList<CountryModel> = ArrayList()
        for (d in mList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.name.lowercase().contains(text.lowercase())) {
                temp.add(d)
            }
        }
        mList = temp
        notifyDataSetChanged()
    }
}
