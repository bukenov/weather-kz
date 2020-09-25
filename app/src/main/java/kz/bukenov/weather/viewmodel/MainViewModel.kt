package kz.bukenov.weather.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.bukenov.weather.App
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.CityRepository
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var cityRepository: CityRepository
    val cities: LiveData<List<City>>

    init {
        (application as App).getDataComponent().inject(this)
        cities = cityRepository.getCities()
    }

    fun findCities(input: String) {
        addDisposable(
            cityRepository.loadCities(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    it.printStackTrace()
                })
        )
    }
}