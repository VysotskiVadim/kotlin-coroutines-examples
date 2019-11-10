package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun avoidGlobalScopeExample() = coroutineScope {
    val job = launch {
        launch {
            println("launch")
            delay(100)
            println("me")
        }
        GlobalScope.launch {
            println("global launch")
            delay(100)
            println("global: me")
        }.join()
    }
    delay(50)
    println("cancelling")
    job.cancel()
    println("who have survived after cancellation?")
    delay(100)
}