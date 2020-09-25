package kz.bukenov.weather.di.module

import dagger.Binds
import dagger.Module
import kz.bukenov.weather.data.repository.*
import kz.bukenov.weather.di.scope.UserScope

@Module
abstract class RepositoryModule {
    @Binds
    @UserScope
    abstract fun bindCityRepository(authRepositoryImpl: CityRepositoryImpl): CityRepository

    @Binds
    @UserScope
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @UserScope
    abstract fun bindInputRepository(inputRepositoryImpl: InputRepositoryImpl): InputRepository
}