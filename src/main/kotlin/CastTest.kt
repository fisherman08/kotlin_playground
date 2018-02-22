import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>){

    val testData = makeData()

    var start = System.currentTimeMillis()
    val dataCastAs :Array<Hashtable<String, String>> = testData as Array<Hashtable<String, String>>
    var end = System.currentTimeMillis()
    println("as: ${end -start}")


    if(testData is Array<*>) {
        var start = System.currentTimeMillis()
        val dataCastManually = testData.filterIsInstance<Hashtable<String, Any>>().toTypedArray()
        var end = System.currentTimeMillis()
        println("manual: ${end -start}")

        println(dataCastManually.size == dataCastAs.size)
    }
}

fun makeData() :Any? {


    val result :MutableList<Hashtable<String, String>> = mutableListOf()

    List(100000) {i ->
        val hash = Hashtable<String, String>()
        hash["key"] = "$i"
        hash["value"] = "value_$i"
        result.add(hash)
    }

    return result.toTypedArray()
}