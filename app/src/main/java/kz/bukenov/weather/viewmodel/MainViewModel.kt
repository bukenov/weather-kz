package kz.bukenov.weather.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.bukenov.weather.App
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.domain.GetCitiesUseCase
import kz.bukenov.weather.domain.GetInputUseCase
import kz.bukenov.weather.domain.LoadCitiesUseCase
import kz.bukenov.weather.domain.LoadWeatherUseCase
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        private const val LIMIT = 3600000
    }

    @Inject
    lateinit var getCitiesUseCase: GetCitiesUseCase

    @Inject
    lateinit var loadCitiesUseCase: LoadCitiesUseCase

    @Inject
    lateinit var loadWeatherUseCase: LoadWeatherUseCase

    @Inject
    lateinit var getInputUseCase: GetInputUseCase
    val cities: LiveData<List<City>>
    val initInput: MutableLiveData<String> = MutableLiveData()
    private val scheduler: Scheduler
    private var needUpdate = true

    init {
        (application as App).getDataComponent().inject(this)
        cities = getCitiesUseCase.invoke()
        // for test use 2 pool size
        val executor = Executors.newFixedThreadPool(2)
        scheduler = Schedulers.from(executor)
        initInput()
    }

    fun findCities(input: String) {
        clearDisposables()
        addDisposable(
            loadCitiesUseCase.invoke(input)
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

    private fun initInput() {
        addDisposable(
            getInputUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    initInput.value = it
                }
        )
    }

    private fun loadWeathers(cities: List<City>) {
        cities.forEach {
            loadWeather(it)
        }
    }

    private fun loadWeather(city: City) {
        addDisposable(
            loadWeatherUseCase.invoke(city)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    it.printStackTrace()
                })
        )
    }
}