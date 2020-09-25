package kz.bukenov.weather.data.network

import io.reactivex.Observable
import kz.bukenov.weather.data.network.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/forecastrss?format=json&u=c")
    fun getWeather(@Query("location") location: String): Observable<WeatherResponse>
}