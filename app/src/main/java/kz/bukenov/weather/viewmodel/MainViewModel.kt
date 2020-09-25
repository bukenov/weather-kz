package kz.bukenov.weather.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.bukenov.weather.App
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.CityRepository
import kz.bukenov.weather.data.repository.WeatherRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        private const val LIMIT = 3600000
    }

    @Inject
    lateinit var cityRepository: CityRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository
    val cities: LiveData<List<City>>
    private val scheduler: Scheduler
    private var needUpdate = true

    init {
        (application as App).getDataComponent().inject(this)
        cities = cityRepository.getCities()
        val executor = Executors.newFixedThreadPool(2)
        scheduler = Schedulers.from(executor)
    }

    fun findCities(input: String) {
        addDisposable(
            cityRepository.loadCities(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadWeathers(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun updateData(cities: List<City>) {
        if (needUpdate) {
            needUpdate = false
            val timestamp = System.currentTimeMillis()
            cities.forEach { city ->
                city.weatherTimestamp?.let {
                    if (timestamp - it > LIMIT) {
                        loadWeather(city)
                    }
                }
            }
        }
    }

    private fun loadWeathers(cities: List<City>) {
        cities.forEach {
            loadWeather(it)
        }
    }

    private fun loadWeather(city: City) {
        addDisposable(
            weatherRepository.loadWeather(city.name)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }
}