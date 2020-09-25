package kz.bukenov.weather.data.repository.datastore

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import kz.bukenov.weather.data.db.CityDao
import kz.bukenov.weather.data.model.City
import javax.inject.Inject

class CityDatabaseStore @Inject constructor(private val cityDao: CityDao) {
    fun getCities(): LiveData<List<City>> {
        return cityDao.getCities()
    }

    fun saveCities(cities: List<City>): Completable {
        return cityDao.deleteAll()
            .andThen(cityDao.insertAll(cities))
    }
}