package kz.bukenov.weather.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.bukenov.weather.R
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.databinding.ItemCityBinding
import kz.bukenov.weather.ui.adapter.callback.CityDiffUtilCallback
import kz.bukenov.weather.ui.holder.CityHolder

class CitiesAdapter(private val context: Context) : RecyclerView.Adapter<CityHolder>() {
    private val items = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemCityBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_city, parent, false)
        return CityHolder(binding)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<City>) {
        val result = DiffUtil.calculateDiff(CityDiffUtilCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }
}