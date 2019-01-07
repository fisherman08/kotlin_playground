

import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.lang.Thread.sleep

fun main(args: Array<String>){

    try {
        runBlocking {
            launch {
                delay(500)
                println(1)
            }

            launch {
                delay(500)

                println(2)
            }
        }

        println("finish")
    } catch (e: Exception){
        e.printStackTrace()
    }


}



