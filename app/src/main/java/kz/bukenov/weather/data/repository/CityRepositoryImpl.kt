package kz.bukenov.weather.data.repository

import io.reactivex.Observable
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.datastore.CityDatabaseStore
import kz.bukenov.weather.data.repository.datastore.CityNetworkStore
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val databaseStore: CityDatabaseStore,
    private val networkStore: CityNetworkStore
) : CityRepository {
    override fun getCities() = databaseStore.getCities()

    override fun loadCities(input: String): Observable<List<City>> {
        return networkStore.getCities(input)
            .flatMap {
                databaseStore.saveCities(it)
                    .andThen(Observable.just(it))
            }
    }
}