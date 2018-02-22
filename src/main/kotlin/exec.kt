import kotlinx.coroutines.experimental.*

import java.lang.Thread.sleep

fun main(args: Array<String>){

    val result = mutableListOf<Int>()

    println("start")

    runBlocking {
        val jobs = List(1000) { i ->
            async() {
                result.add(hoge(i))
            }
        }

        jobs.forEach { it.join() }

    }

    //result.sortBy { i -> i }
    result.sort()
    println("finish")

}

suspend fun hoge(i :Int) :Int{
    println(i)
    delay(500)
    return i
}