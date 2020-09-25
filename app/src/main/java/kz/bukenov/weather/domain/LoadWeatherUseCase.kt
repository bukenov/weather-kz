package kz.bukenov.weather.domain

import io.reactivex.Completable
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.WeatherRepository
import javax.inject.Inject

class LoadWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : (City) -> Completable {
    override fun invoke(city: City) = weatherRepository.loadWeather(city.name)
}