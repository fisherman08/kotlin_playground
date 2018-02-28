
import java.io.FileInputStream
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddressBase

fun main(args :Array<String>){

    var workbook :Workbook? = null

    try {

        workbook = WorkbookFactory.create(FileInputStream(Paths.get(String.javaClass.getResource("/poi/test.xlsx").toURI()).toFile()))

        val sheet :Sheet? = workbook["結合テスト"]

        if(sheet != null){
            for(i in 0..4) {
                println(sheet[i, 0])
            }
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
    val cell = this.getRow(row)?.getCell(column) ?: return null

    for(mergedRegion in this.mergedRegions) {
        if(mergedRegion.isInRange(cell) &&  !mergedRegion.isFirstCell(cell)) {
            return this[mergedRegion.firstRow, mergedRegion.firstColumn]
        }
    }
    return cell
}

fun CellRangeAddressBase.isFirstCell(cell :Cell) :Boolean {
    return(cell.rowIndex == this.firstRow && cell.columnIndex == this.firstColumn)
}