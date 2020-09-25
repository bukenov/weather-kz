package kz.bukenov.weather.domain

import io.reactivex.Observable
import kz.bukenov.weather.data.model.City
import kz.bukenov.weather.data.repository.CityRepository
import kz.bukenov.weather.data.repository.InputRepository
import javax.inject.Inject

class LoadCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val inputRepository: InputRepository
) : (String) -> Observable<List<City>> {
    override fun invoke(input: String): Observable<List<City>> {
        return cityRepository.loadCities(input)
            .flatMap { inputRepository.saveInput(input).andThen(Observable.just(it)) }
    }
}