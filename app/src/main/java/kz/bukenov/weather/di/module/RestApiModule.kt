package kz.bukenov.weather.di.module

import dagger.Module
import dagger.Provides
import kz.bukenov.weather.data.network.PlacesApi
import kz.bukenov.weather.data.network.WeatherApi
import kz.bukenov.weather.di.scope.AppScope
import retrofit2.Retrofit
import javax.inject.Named

@Module
class RestApiModule {
    @Provides
    @AppScope
    fun providePlacesApi(@Named("places") retrofit: Retrofit): PlacesApi {
        return retrofit.create(PlacesApi::class.java)
    }

    @Provides
    @AppScope
    fun provideWeatherApi(@Named("weather") retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}