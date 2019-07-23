package com.razan.weatherkotlin.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.razan.weatherkotlin.databinding.CountryItemBinding
import com.razan.weatherkotlin.model.Country

class CountriesListAdapter(private val callback: CountryClickCallback):
    RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder>()  {

    private var countries: MutableList<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater)

        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) = holder.bind(countries[position], callback)

    class CountryViewHolder(private val binding: CountryItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country, listener: CountryClickCallback) {
            with(binding) {
                countryItem = item
                countryCode = item.alpha2Code // TODO: double check this
                executePendingBindings() // use this only with expressions inside xml
                callback = listener
            }
        }
    }

    fun setItems(countries: List<Country>) {
        val startPosition = this.countries.size
        this.countries.addAll(countries)
        notifyItemRangeChanged(startPosition, countries.size)
    }
}
