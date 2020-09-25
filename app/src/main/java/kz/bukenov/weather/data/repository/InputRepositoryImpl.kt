package kz.bukenov.weather.data.repository

import kz.bukenov.weather.data.repository.datastore.InputDatabaseStore
import javax.inject.Inject

class InputRepositoryImpl @Inject constructor(
    private val databaseStore: InputDatabaseStore
) : InputRepository {
    override fun getInput() = databaseStore.getCurrentInput()

    override fun saveInput(input: String) = databaseStore.saveCurrentInput(input)
}