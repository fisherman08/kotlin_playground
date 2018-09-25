

fun doSomething(s: String, lambda: String.() -> Unit) {
    val res = s + "ccccc"
    res.lambda()
}


fun String.と叫ぶ(){
    println(this)
}

fun main(args: Array<String>){

    try {


        doSomething("unko") {
            println(this)
        }

    } catch(e :Exception) {
        e.printStackTrace()
    }

}
