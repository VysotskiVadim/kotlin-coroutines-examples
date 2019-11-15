package dev.vadzimv.kotlincoroutinesexamples

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.text_and_button.*
import kotlinx.coroutines.delay

class CounterAtViewModelExampleFragment : TextButtonFragment() {

    private val viewModel: CounterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTitle("counter at view model")
        button.visibility = View.GONE
        text.visibility = View.VISIBLE
        viewModel.counter.observe(viewLifecycleOwner) {
            text.text = it.toString()
        }
    }
}

class CounterViewModel : ViewModel() {
    val counter: LiveData<Int> = liveData {
        var i = 0
        while (true) {
            delay(1000)
            emit(i++)
        }
    }
}