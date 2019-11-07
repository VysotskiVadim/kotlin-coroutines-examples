package dev.vadzimv.kotlincoroutinesexamples

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun suspendVsLaunchTask() = coroutineScope {
    launch {
        delay(200)
        print("1")
    }
    launch {
        delay(100)
        print("2")
    }
    delay(300)
    launch {
        delay(50)
        print("3")
    }
}