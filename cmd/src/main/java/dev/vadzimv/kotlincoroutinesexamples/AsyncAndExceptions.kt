package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun asyncAndExceptions() = coroutineScope {
    val deffer = async<Int> {
        delay(10)
        throw Error("async oops")
    }
    try {
        val result = deffer.await()
        println(result)
    } catch (e: Error) {
        println("error in async is ${e.message}")
    }
}