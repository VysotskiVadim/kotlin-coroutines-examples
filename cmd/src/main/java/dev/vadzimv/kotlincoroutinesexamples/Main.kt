package dev.vadzimv.kotlincoroutinesexamples

import contextExample
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import launchExample
import suspendExample

fun main() {
    //System.setProperty(DEBUG_PROPERTY_NAME, DEBUG_PROPERTY_VALUE_ON)
    runExample("suspend") { suspendExample() }
    runExample("launch") {
        coroutineScope {
            launchExample()
        }
    }
    runExample("suspend va launch task") { suspendVsLaunchTask() }
    runExample("context") { coroutineScope { contextExample() } }
    runExample("structured concurrency") { structuredConcurrency() }
    runExample("custom scope") { customScope() }
    runExample("avoid global scope") { avoidGlobalScopeExample() }
    runExample("cancellation under the hood") { cancellationUnderTheHood() }
    runExample("suspend function and exception") { suspendFunctionsAndExceptionsExample() }
    runExample("async and exceptions") { asyncAndExceptions() }
    runExample("supervision example") { supervisionExample() }
    runExample("supervision example 2") { supervisionExample2() }
    runExample("supervision and cancellation") { supervisionAndCancellation() }
    runExample("supervision with few children") { supervisionWithFewChildren() }
    runExample("callback order example") { callbacksOrderExample() }
}

fun runExample(name: String, example: suspend () -> Unit) {
    println("starting $name")
    try {
        runBlocking { example.invoke() }
    } catch (e: Throwable) {
        println("example $name failed with error: $e")
    }
    println("\n$name finished")
    println("---------------------------")
}