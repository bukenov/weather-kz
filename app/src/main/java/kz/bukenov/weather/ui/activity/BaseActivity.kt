package kz.bukenov.weather.ui.activity

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    override fun onPause() {
        disposables.clear()
        super.onPause()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}