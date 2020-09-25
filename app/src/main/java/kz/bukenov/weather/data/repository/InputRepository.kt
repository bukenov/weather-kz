package kz.bukenov.weather.data.repository

import io.reactivex.Completable
import io.reactivex.Observable

interface InputRepository {
    fun getInput(): Observable<String>

    fun saveInput(input: String): Completable
}