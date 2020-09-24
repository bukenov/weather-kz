package kz.bukenov.weather.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.bukenov.weather.App
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.CityRepository
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var cityRepository: CityRepository
    val cities: MutableLiveData<List<City>> = MutableLiveData(listOf())

    init {
        (application as App).getDataComponent().inject(this)
    }

    fun findCities(input: String) {
        addDisposable(
            cityRepository.getCities(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    cities.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }
}