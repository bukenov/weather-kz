package kz.bukenov.weather.domain

import io.reactivex.Observable
import kz.bukenov.weather.data.repository.InputRepository
import javax.inject.Inject

class GetInputUseCase @Inject constructor(
    private val inputRepository: InputRepository
) : () -> Observable<String> {
    override fun invoke() = inputRepository.getInput()
}