package kz.bukenov.weather.data.repository.datastore

import io.reactivex.Flowable
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.network.PlacesApi
import javax.inject.Inject

class CityNetworkStore @Inject constructor(private val placesApi: PlacesApi) {
    companion object {
        private const val key = "AIzaSyCPMD_H3KWcAJa9PX3b-NTzo6z2OsY90X0"
    }

    fun getCities(input: String): Flowable<List<City>> {
        return placesApi.getAutocomplete(input, key)
            .map { response ->
                response.predictions.map { prediction ->
                    City(prediction.structured_formatting.main_text)
                }
            }
    }
}