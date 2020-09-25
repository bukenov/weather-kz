package kz.bukenov.weather.ui.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kz.bukenov.weather.data.model.City

class CityDiffUtilCallback(
    private val oldList: List<City>,
    private val newList: List<City>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}