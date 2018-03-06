
import com.github.kittinunf.fuel.core.DataPart
import com.github.kittinunf.fuel.core.FuelManager

import com.github.kittinunf.fuel.httpUpload
import java.io.File

fun main(args: Array<String>){

    try {

        FuelManager.instance.basePath = "https://www.google.co.jp"
        FuelManager.instance.baseHeaders = mapOf("goe" to "rr")


        // Stringで撮ってくるバージョン
        val (request, response, result) = "esi".httpUpload(
                parameters = listOf(Pair("nextpage", "api/pj/upload.json"), Pair("pj_id", "1"), Pair("encode_type", "MS932"))
        ).dataParts({request, url ->
            listOf(
                    DataPart(File(String.javaClass.getResource("/csv/test.csv").toURI()), "uploadfile")
            )
        }).timeout(100000).timeoutRead(100000).responseString(charset = Charsets.UTF_8)


        val (d, err) = result
        println(d)
    } catch(e :Exception) {
        e.printStackTrace()
    }

}
