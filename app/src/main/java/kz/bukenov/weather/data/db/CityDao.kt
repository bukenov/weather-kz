package kz.bukenov.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(city: City): Completable
}