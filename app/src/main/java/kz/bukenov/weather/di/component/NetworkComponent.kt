package kz.bukenov.weather.di.component

import android.content.Context
import dagger.Subcomponent
import kz.bukenov.weather.di.module.NetworkModule
import kz.bukenov.weather.di.module.RestApiModule
import kz.bukenov.weather.di.scope.AppScope

@AppScope
@Subcomponent(modules = [NetworkModule::class, RestApiModule::class])
interface NetworkComponent {
    fun context(): Context

    fun plus(): DataComponent
}