package kz.bukenov.weather.ui.holder

import androidx.recyclerview.widget.RecyclerView
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.databinding.ItemCityBinding

class CityHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(city: City) {
        binding.city = city
    }
}