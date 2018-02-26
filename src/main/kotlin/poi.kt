
import java.io.FileInputStream
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.*

fun main(args :Array<String>){

    var workbook :Workbook? = null

    try {

        workbook = WorkbookFactory.create(FileInputStream(Paths.get(String.javaClass.getResource("/poi/test.xlsx").toURI()).toFile()))

        val sheet :Sheet? = workbook["新シート"]

        if(sheet != null){
            val cell :Cell? = sheet[2, 2]
            println(cell?.numericCellValue?.toInt())
        }


    } catch (e: Exception){
        e.printStackTrace()
    } finally {
        workbook?.close()
    }


}

operator fun Workbook.get(sheetName :String) :Sheet? {
    return this.getSheet(sheetName)
}

operator fun Sheet.get(row :Int, column :Int) :Cell? {
    return this.getRow(row)?.getCell(column)
}