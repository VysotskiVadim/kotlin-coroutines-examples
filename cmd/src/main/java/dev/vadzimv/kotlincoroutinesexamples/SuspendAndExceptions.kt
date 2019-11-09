package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.delay

suspend fun suspendFunctionsAndExceptionsExample() {
    try {
        exceptionAfterDelay()
    } catch (e: Error) {
        println("caught error ${e.message}")
    }
}

suspend fun exceptionAfterDelay() {
    delay(20)
    throw Error("Opps")
}