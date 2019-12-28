package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.*

suspend fun callbacksOrderExample() = coroutineScope {
    val result = withContext(Dispatchers.IO) {
        coroutineContext[Job]?.invokeOnCompletion { println("invokeOnCompletion callback") }
        delay(300)
        "completed"
    }
    println("result is $result")
}