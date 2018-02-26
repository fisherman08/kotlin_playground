
import java.io.FileInputStream
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.*

fun main(args :Array<String>){

    var workbook :Workbook? = null

    try {

        workbook = WorkbookFactory.create(FileInputStream(Paths.get(String.javaClass.getResource("/poi/test.xlsx").toURI()).toFile()))

        val sheet :Sheet? = workbook.getSheet("新シート")

        if(sheet != null){
            val cell :Cell? = sheet.getRow(2)?.getCell(2)
            println(cell?.numericCellValue?.toInt())
        }


    } catch (e: Exception){
        e.printStackTrace()
    } finally {
        workbook?.close()
    }


}