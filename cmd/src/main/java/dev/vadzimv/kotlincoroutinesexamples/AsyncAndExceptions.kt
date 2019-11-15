package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun asyncAndExceptions() = coroutineScope {
    val deferred = async<Int> {
        delay(10)
        throw Error("async oops")
    }
    try {
        val result = deferred.await()
        println(result)
    } catch (e: Error) {
        println("error in async is ${e.message}")
    }
}