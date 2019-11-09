package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.*

suspend fun asyncAndExceptions() = supervisorScope {
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