package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

suspend fun supervisionExample() {
    with(CoroutineScope(coroutineContext + SupervisorJob())) {
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
}

suspend fun supervisionExample2() {
    supervisorScope {
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
}

suspend fun supervisionAndCancellation() = coroutineScope {
    val job = launch {
        with(CoroutineScope(coroutineContext + SupervisorJob())) {
            val deferred = async<Int> {
                delay(100)
                throw Error("async oops")
            }
            try {
                val result = deferred.await()
                println(result)
            } catch (e: Error) {
                println("error in async is ${e.message}")
            } catch (ce: CancellationException) {
                println("cancelled")
                throw ce
            }
        }
    }
    delay(50)
    job.cancel()
    println("Are you still alive?")
    delay(100)
}

suspend fun supervisionWithFewChildren() = supervisorScope {
    val printExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("coroutine failed with error: ${throwable.message}")
    }
    launch(printExceptionHandler) {
        delay(30)
        throw Error("oops")
    }
    launch {
        repeat(5) {
            delay(10)
            println("iteration #$it")
        }
    }
}