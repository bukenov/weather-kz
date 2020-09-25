package kz.bukenov.weather.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import kz.bukenov.weather.data.model.City

interface CityRepository {
    fun getCities(): LiveData<List<City>>

    fun loadCities(input: String): Observable<List<City>>
}