package kz.bukenov.weather.di.module

import dagger.Binds
import dagger.Module
import kz.bukenov.weather.data.repository.CityRepository
import kz.bukenov.weather.data.repository.CityRepositoryImpl
import kz.bukenov.weather.di.scope.UserScope

@Module
abstract class RepositoryModule {
    @Binds
    @UserScope
    abstract fun bindCityRepository(authRepositoryImpl: CityRepositoryImpl): CityRepository
}