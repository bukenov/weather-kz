package kz.bukenov.weather.data.repository

import io.reactivex.Flowable
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.datastore.CityNetworkStore
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val networkStore: CityNetworkStore
) : CityRepository {

    override fun getCities(input: String): Flowable<List<City>> {
        return networkStore.getCities(input)
    }
}