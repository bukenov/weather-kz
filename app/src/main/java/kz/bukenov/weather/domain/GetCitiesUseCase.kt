package kz.bukenov.weather.domain

import androidx.lifecycle.LiveData
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.CityRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository
) : () -> LiveData<List<City>> {
    override fun invoke() = cityRepository.getCities()
}