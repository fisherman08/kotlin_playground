import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet

fun main(args: Array<String>){

    try {

        FuelManager.instance.basePath = "https://www.google.co.jp"
        FuelManager.instance.baseHeaders = mapOf("goe" to "rr")

        // Stringで撮ってくるバージョン
        val (request, response, result) = "/".httpGet().responseString(charset = Charsets.UTF_8)

        // Gsonで取得するバージョン
        //data class HttpBinUserAgentModel(var userAgent: String = "")
        //val (request, response, result) = "/".httpGet().responseObject<HttpBinUserAgentModel>()
        //Fuel.get("/").responseObject<HttpBinUserAgentModel> { _, _, result ->
        //}

        val (d, err) = result
        println(d)
    } catch(e :Exception) {
        e.printStackTrace()
    }

}
