import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.newInstance


class Stap(val message: String, val number: Int) {

    fun print(){
        System.out.println("${number}: $message")
    }
}

fun main(args: Array<String>){

    try {
        val kodein = Kodein {
            bind<String>(tag = "abc") with instance("uno")
            bind<Int>(tag = "number") with instance(1)
            bind<Int>(tag = "number2") with instance(2)
        }

        val stap by kodein.newInstance { Stap(instance(tag = "abc"), instance("number2")) }

        stap.print()

    } catch(e :Exception) {
        e.printStackTrace()
    }

}
