package kz.bukenov.weather.data.repository.datastore

import io.reactivex.Observable
import kz.bukenov.weather.data.network.WeatherApi
import kz.bukenov.weather.data.network.response.Forecast
import javax.inject.Inject

class WeatherNetworkStore @Inject constructor(private val weatherApi: WeatherApi) {
    fun getCurrentWeather(cityName: String): Observable<Forecast> {
        return weatherApi.getWeather("$cityName,kz")
            .flatMap {
                if (it.forecasts.isNotEmpty()) {
                    Observable.just(it.forecasts[0])
                } else {
                    Observable.empty()
                }
            }
    }
}