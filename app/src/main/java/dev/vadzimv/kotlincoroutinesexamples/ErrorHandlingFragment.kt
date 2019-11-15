package dev.vadzimv.kotlincoroutinesexamples

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.text_and_button.*
import kotlinx.coroutines.*
import java.math.BigInteger
import java.util.*
import kotlin.coroutines.CoroutineContext

class ErrorHandlingFragment : Fragment() {

    private val viewModel: ErrorHandlerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.text_and_button, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setActivityTitle("Error Handling")
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                ErrorHandlerViewModel.State.ReadyToStart -> {
                    text.text = "click start to begin"
                    button.text = "start"
                    button.visibility = View.VISIBLE
                }
                ErrorHandlerViewModel.State.Loading -> {
                    text.text = "loading..."
                    button.visibility = View.GONE
                }
                is ErrorHandlerViewModel.State.Error -> {
                    text.text = "oops, error: ${it.e.message} \n ${Log.getStackTraceString(it.e)}"
                    button.visibility = View.VISIBLE
                    button.text = "restart"
                }
                is ErrorHandlerViewModel.State.Result -> {
                    text.text = "result is ${it.result}"
                    button.visibility = View.VISIBLE
                    button.text = "recalculate"
                }
            }
        }
        button.setOnClickListener { viewModel.buttonClicked() }
    }
}

class ErrorHandlerViewModel : ViewModel(), CoroutineScope {

    @Volatile
    private var nextCalculationSuccessful = false

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    private val _state = MutableLiveData<State>().apply { value = State.ReadyToStart }
    val state: LiveData<State> = _state

    sealed class State {
        object Loading : State()
        data class Error(val e: Throwable) : State()
        object ReadyToStart : State()
        data class Result(val result: BigInteger) : State()
    }

    fun buttonClicked() {
        launch {
            try {
                _state.value = State.Loading
                throwErrorIfNeeded()
                val result = getRandomNumber()
                _state.value = State.Result(result)
            } catch (e: Throwable) {
                _state.value = State.Error(e)
            }

        }
    }

    private suspend fun throwErrorIfNeeded() {
        nextCalculationSuccessful = nextCalculationSuccessful.not()
        if (nextCalculationSuccessful) {
            delay(900)
            throw Throwable("Sorry, not this time")
        }
    }
}

private suspend fun getRandomNumber(): BigInteger = withContext(Dispatchers.Default) {
    delay(100)
    BigInteger.probablePrime(4096, Random())
}