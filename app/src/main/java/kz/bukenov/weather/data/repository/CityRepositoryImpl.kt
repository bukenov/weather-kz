package kz.bukenov.weather.data.repository

import io.reactivex.Completable
import kz.bukenov.weather.data.repository.datastore.CityDatabaseStore
import kz.bukenov.weather.data.repository.datastore.CityNetworkStore
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val databaseStore: CityDatabaseStore,
    private val networkStore: CityNetworkStore
) : CityRepository {
    override fun getCities() = databaseStore.getCities()

    override fun loadCities(input: String): Completable {
        return networkStore.getCities(input)
            .flatMapCompletable {
                databaseStore.saveCities(it)
            }
    }
}