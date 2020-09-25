package kz.bukenov.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import kz.bukenov.weather.data.model.City

@Dao
interface CityDao {
    @Query("SELECT * from City")
    fun getCities(): LiveData<List<City>>

    @Insert
    fun insertAll(cities: List<City>): Completable

    @Query("DELETE FROM City")
    fun deleteAll(): Completable
}