package kz.bukenov.weather.data.repository

import io.reactivex.Completable

interface WeatherRepository {
    fun loadWeather(cityName: String): Completable
}