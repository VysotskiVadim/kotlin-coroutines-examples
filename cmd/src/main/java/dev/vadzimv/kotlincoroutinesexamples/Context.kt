import kotlinx.coroutines.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun CoroutineScope.contextExample() {
    launch(Dispatchers.IO + CoroutineName("name1")) {
        log("a")
    }
    launch(Dispatchers.Default + CoroutineName("name2")) {
        log("b")
        launch {
            log("c")
        }
    }
}