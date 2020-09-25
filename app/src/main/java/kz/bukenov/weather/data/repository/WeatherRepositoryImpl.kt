package kz.bukenov.weather.data.repository

import io.reactivex.Completable
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.datastore.CityDatabaseStore
import kz.bukenov.weather.data.repository.datastore.WeatherNetworkStore
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val networkStore: WeatherNetworkStore,
    private val cityDatabaseStore: CityDatabaseStore
) : WeatherRepository {
    override fun loadWeather(cityName: String): Completable {
        return networkStore.getCurrentWeather(cityName)
            .flatMapCompletable {
                cityDatabaseStore.update(City(cityName, it.low, it.high, it.text))
            }
    }
}