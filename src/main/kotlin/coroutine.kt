
import kotlinx.coroutines.*
import java.lang.Thread.sleep

fun main(args: Array<String>) = runBlocking {

    try {

        var res: Int = 0
        launch {
            hoge(1)

            res = hoge(2)
        }.join()

        println("${res * 100}")
        println("finish")


    } catch (e: Exception) {
        e.printStackTrace()
    }


}

suspend fun hoge(num: Int): Int = coroutineScope {
    launch {
        val scope = this
        val context = scope.coroutineContext

        delay(500)
        println(num)
    }

    num
}


