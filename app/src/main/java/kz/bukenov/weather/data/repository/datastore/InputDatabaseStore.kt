package kz.bukenov.weather.data.repository.datastore

import kz.bukenov.weather.data.db.InputDao
import kz.bukenov.weather.data.model.Input
import javax.inject.Inject

class InputDatabaseStore @Inject constructor(private val inputDao: InputDao) {
    companion object {
        private const val CURRENT_ID = 1
    }

    fun getCurrentInput() = inputDao.getInput(CURRENT_ID)

    fun saveCurrentInput(input: String) = inputDao.upsert(Input(CURRENT_ID, input))
}