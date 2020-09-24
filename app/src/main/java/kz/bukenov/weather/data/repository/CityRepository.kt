package kz.bukenov.weather.data.repository

import io.reactivex.Flowable
import kz.bukenov.weather.data.model.City

interface CityRepository {
    fun getCities(input: String): Flowable<List<City>>
}