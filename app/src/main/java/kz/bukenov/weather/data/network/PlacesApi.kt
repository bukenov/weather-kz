package kz.bukenov.weather.data.network

import io.reactivex.Flowable
import kz.bukenov.weather.data.network.response.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {
    @GET("/maps/api/place/autocomplete/json?types=(cities)&components=country:kz&language=ru")
    fun getAutocomplete(
        @Query("input") input: String,
        @Query("key") key: String
    ): Flowable<PlacesResponse>
}