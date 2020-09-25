package kz.bukenov.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        clearDisposables()
        super.onCleared()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun clearDisposables() {
        disposables.clear()
    }
}