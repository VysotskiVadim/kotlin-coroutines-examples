import kotlinx.coroutines.delay

suspend fun suspendExample() {
    val first = firstTask()
    println("first task complete")
    val second = secondTask()
    println("second task completed")
    println("result is ${first + second}")
}

suspend fun firstTask(): Int {
    delay(100)
    return 1
}

suspend fun secondTask(): Int {
    delay(100)
    return 2
}

class UnderstandingLevel1 {

    fun firstTask(continuation: (Int) -> Unit) {
        // delay
        continuation.invoke(1)
    }

    fun secondTask(continuation: (Int) -> Unit) {
        continuation.invoke(2)
    }

    fun example(continuation: () -> Unit) {
        firstTask { first ->
            println("first task complete")
            secondTask { second ->
                println("second task completed")
                println("result is ${first + second}")
                continuation.invoke()
            }
        }
    }
}