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
    @Inject
    lateinit var cityRepository: CityRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository
    val cities: LiveData<List<City>>
    private val scheduler: Scheduler

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

    private fun loadWeathers(cities: List<City>) {
        cities.forEach {
            addDisposable(
                weatherRepository.loadWeather(it.name)
                    .subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }
}