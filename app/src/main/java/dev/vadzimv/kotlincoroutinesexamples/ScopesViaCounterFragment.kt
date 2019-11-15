package dev.vadzimv.kotlincoroutinesexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.scopes_via_counter.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ScopesViaCounterFragment : Fragment() {

    private val viewModelV2: CounterViewModelV2 by viewModels()
    private val viewModelV1: CounterViewModelV1 by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.scopes_via_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setActivityTitle("scopes via counter")
        viewModelV2.liveDataCoroutineBuilderCounter.observe(viewLifecycleOwner) {
            viewModelV2LiveDataValue.text = it.toString()
        }
        viewModelV2.viewModelScopeBasedCounter.observe(viewLifecycleOwner) {
            viewModelV2ScopeValue.text = it.toString()
        }
        viewModelV1.customScopeBasedCounter.observe(viewLifecycleOwner) {
            viewModelV1Value.text = it.toString()
        }
    }
}

class CounterViewModelV2 : ViewModel() {
    val liveDataCoroutineBuilderCounter: LiveData<Int> = liveData {
        var i = 0
        emit(i)
        while (true) {
            delay(1000)
            emit(i++)
        }
    }

    val _viewModelScopeBasedCounter = MutableLiveData<Int>()
    val viewModelScopeBasedCounter: LiveData<Int> get() = _viewModelScopeBasedCounter

    init {
        viewModelScope.launch {
            var i = 0
            do {
                _viewModelScopeBasedCounter.value = i
                i++
                delay(1000)
            } while (true)
        }
    }
}

class CounterViewModelV1 : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    private val _viewModelScopeBasedCounter = MutableLiveData<Int>()
    val customScopeBasedCounter: LiveData<Int> get() = _viewModelScopeBasedCounter

    init {
        launch {
            var i = 0
            do {
                _viewModelScopeBasedCounter.value = i
                i++
                delay(1000)
            } while (true)
        }
    }
}