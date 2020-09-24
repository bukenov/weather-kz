package kz.bukenov.weather

import android.app.Application
import kz.bukenov.weather.di.component.ApplicationComponent
import kz.bukenov.weather.di.component.DaggerApplicationComponent
import kz.bukenov.weather.di.component.DataComponent
import kz.bukenov.weather.di.component.NetworkComponent
import kz.bukenov.weather.di.module.ApplicationModule

class App : Application() {
    private lateinit var applicationComponent: ApplicationComponent
    private var networkComponent: NetworkComponent? = null
    private var dataComponent: DataComponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent() = applicationComponent

    private fun getNetworkComponent(): NetworkComponent {
        if (networkComponent == null) {
            networkComponent = applicationComponent.plus()
        }
        return networkComponent!!
    }

    fun getDataComponent(): DataComponent {
        if (dataComponent == null) {
            dataComponent = getNetworkComponent().plus()
        }
        return dataComponent!!
    }
}