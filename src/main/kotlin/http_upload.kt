
import com.github.kittinunf.fuel.core.DataPart
import com.github.kittinunf.fuel.core.FuelManager

import com.github.kittinunf.fuel.httpUpload
import java.io.File

fun main(args: Array<String>){

    try {

        FuelManager.instance.basePath = "http://ky-mac-mini.local:8888/local"
        FuelManager.instance.baseHeaders = mapOf("goe" to "rr", "ESI_COMPANY_ID" to "99", "ESI_USER_ID" to "211", "Authorization" to "Bearer kvuWfHXtU2AdhNjat4kw")


        // Stringで撮ってくるバージョン
        val (request, response, result) = "esi".httpUpload(
                parameters = listOf(Pair("nextpage", "api/user/image_z.json"), Pair("pj_id", "1"), Pair("encode_type", "MS932"))
        ).dataParts({request, url ->
            listOf(
                    //DataPart(File(String.javaClass.getResource("/csv/test.csv").toURI()), "uploadfile")
                    DataPart(File("~//Documents/windows_share/workspace/zipResize/99.zip"), "uploadfile")
            )
        }).timeout(5000).timeoutRead(100000).responseString(charset = Charsets.UTF_8)


        val (d, err) = result
        println(d)
        println(err)
    } catch(e :Exception) {
        e.printStackTrace()
    }

}
