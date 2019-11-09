package dev.vadzimv.kotlincoroutinesexamples

import contextExample
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import launchExample
import suspendExample

fun main() {
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