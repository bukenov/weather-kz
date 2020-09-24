package kz.bukenov.weather.di.component

import dagger.Subcomponent
import kz.bukenov.weather.di.module.RepositoryModule
import kz.bukenov.weather.di.scope.UserScope
import kz.bukenov.weather.viewmodel.MainViewModel

@UserScope
@Subcomponent(modules = [RepositoryModule::class])
interface DataComponent {
    fun inject(mainViewModel: MainViewModel)
}