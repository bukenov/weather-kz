package kz.bukenov.weather.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_main.*
import kz.bukenov.weather.R
import kz.bukenov.weather.databinding.ActivityMainBinding
import kz.bukenov.weather.ui.adapter.CitiesAdapter
import kz.bukenov.weather.viewmodel.MainViewModel
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter = CitiesAdapter(this)
        recyclerView.adapter = adapter
        viewModel.cities.observe(this, {
            adapter.updateItems(it)
            viewModel.updateData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        addDisposable(
            RxTextView.textChanges(input)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { it.length >= 2 }
                .subscribe {
                    viewModel.findCities(it.toString())
                }
        )
    }
}