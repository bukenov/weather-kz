package kz.bukenov.weather.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class City(
    @PrimaryKey
    val name: String,
    var weatherLow: Int? = null,
    var weatherHigh: Int? = null,
    var weatherText: Int? = null,
) : Parcelable