package kz.bukenov.weather.di.component

import android.content.Context
import dagger.Component
import kz.bukenov.weather.App
import kz.bukenov.weather.di.module.ApplicationModule
import kz.bukenov.weather.di.module.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RoomModule::class])
interface ApplicationComponent {
    fun context(): Context

    fun inject(app: App);

    fun plus(): NetworkComponent
}