package kz.bukenov.weather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import kz.bukenov.weather.data.model.Input

@Dao
interface InputDao {
    @Query("SELECT input FROM Input WHERE id = :id")
    fun getInput(id: Int): Observable<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(input: Input): Completable
}