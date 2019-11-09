package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

suspend fun customScope() {
    val screen = Screen()
    screen.startTimer()
    delay(100)
    screen.clear()
}

class Screen : CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Default

    fun startTimer() {
        var timerState = 0
        launch {
            while (true) {
                delay(10)
                println("timer ${timerState++}")
            }
        }
    }

    fun clear() {
        coroutineContext.cancel()
    }
}