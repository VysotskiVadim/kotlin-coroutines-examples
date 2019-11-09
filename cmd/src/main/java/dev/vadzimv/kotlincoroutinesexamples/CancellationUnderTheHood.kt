package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.*

suspend fun cancellationUnderTheHood() = coroutineScope {
    val job = launch {
        launch {
            try {
                while (true) {
                    println("a")
                    delay(1)
                }
            } catch (ce: CancellationException) {
                println("caught cancellation exception")
            }
        }
        launch {
            while (true) {
                println("b")
                yield()
            }
        }
        launch(Dispatchers.Default) {
            while (isActive) {
                println("c")
            }
        }
    }
    delay(3)
    job.cancel()
}