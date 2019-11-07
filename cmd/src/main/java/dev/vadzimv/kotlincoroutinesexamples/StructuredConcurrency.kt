package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun structuredConcurrency() = coroutineScope {
    val job = launch {
        launch {
            while (true) {
                delay(100)
                println("working")
            }
        }
        launch {
            while (true) {
                delay(100)
                println("working")
            }
        }
    }
    delay(250)
    println("cancel")
    job.cancel()
    delay(250)
}