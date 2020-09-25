package kz.bukenov.weather.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import kz.bukenov.weather.data.db.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
    private val appDatabase = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideCityDao() = appDatabase.cityDao()

    @Provides
    @Singleton
    fun provideInputDao() = appDatabase.inputDao()
}