import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>){

    try {

        val process_builder = ProcessBuilder(arrayListOf()

        )

        val process = process_builder.start()
        val br = BufferedReader(InputStreamReader(process.inputStream))

        val exit_code = process.waitFor()
        val foge = br.readText()
        System.out.println(exit_code)
        System.out.println(foge)

    } catch (e: Exception){
        e.printStackTrace()
    }


}



