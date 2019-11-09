package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

suspend fun customScope() {
    val screen = Screen()
    screen.startTimer()
    delay(15)
    screen.clear()
}

class Screen : CoroutineScope {

    override val coroutineContext: CoroutineContext =
        Job() + Dispatchers.Default

    fun clear() {
        coroutineContext.cancel()
    }

    fun startTimer() {
        var timerState = 0
        launch {
            while (true) {
                delay(1)
                println("timer ${timerState++}")
            }
        }
    }
}