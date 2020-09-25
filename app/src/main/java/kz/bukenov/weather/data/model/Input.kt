package kz.bukenov.weather.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Input(
    @PrimaryKey
    val id: Int,
    val input: String
) : Parcelable