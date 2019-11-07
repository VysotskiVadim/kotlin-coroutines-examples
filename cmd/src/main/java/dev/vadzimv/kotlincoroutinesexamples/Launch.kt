import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CoroutineScope.launchExample() {
    launch {
        delay(1000)
        print("world")
    }
    launch {
        delay(500)
        print("hello ")
    }
}
