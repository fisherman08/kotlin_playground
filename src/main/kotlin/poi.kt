
import java.io.FileInputStream
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddressBase

fun main(args: Array<String>) {

    var workbook: Workbook? = null

    try {

        workbook = WorkbookFactory.create(FileInputStream(String.javaClass.getResource("/poi/test.xlsx").file))

        val sheet: Sheet = workbook["新シート"] ?: workbook.createSheet()

        for (i in sheet.firstRowNum..sheet.lastRowNum) {
            println("${sheet[i, 3]?.rowIndex}: ${sheet[i, 0] ?: ""}")
        }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        workbook?.close()
    }


}

/**
 * workbook["シート名"]でアクセスできるようにする
 */
operator fun Workbook.get(sheetName :String) :Sheet? {
    return this.getSheet(sheetName)
}

operator fun Sheet.get(row :Int) :Row? {
    return getRow(row)
}

/**
 * sheet[row, column]でアクセスできるようにする
 */
operator fun Sheet.get(row :Int, column :Int) :Cell? {
    val cell = this.getRow(row)?.getCell(column) ?: return null

    for(mergedRegion in this.mergedRegions) {
        if(mergedRegion.isInRange(cell)) {
            if(!mergedRegion.isFirstCell(cell)) {
                // 結合されたセルだったら本来のセルは無視して結合された中の一番左上のセルの値で上書きする
                val cellToDisplay = this[mergedRegion.firstRow, mergedRegion.firstColumn] ?: cell
                cell.copyValues(cellToDisplay)
            }
            break
        }
    }

    return cell
}

fun Row.getCells() :Array<Cell> {
    val result :MutableList<Cell> = mutableListOf()
    for(i in firstCellNum..lastCellNum) {
        val cell = sheet[rowNum, i] ?: continue
        result.add(cell)
    }
    return result.toTypedArray()
}


/**
 * セルタイプに応じてセルの値をコピーする
 */
fun Cell.copyValues(source :Cell){
    when (source.cellTypeEnum) {
        CellType.NUMERIC -> setCellValue(source.numericCellValue)
        CellType.STRING  -> setCellValue(source.stringCellValue)
        CellType.BOOLEAN -> setCellValue(source.booleanCellValue)
        CellType.FORMULA -> setCellFormula(source.cellFormula)
        else -> {}
    }
}

/**
 * 結合セル内の最初のセル(一番左上のセル)かどうかを判定
 */
fun CellRangeAddressBase.isFirstCell(cell :Cell) :Boolean {
    return(cell.rowIndex == this.firstRow && cell.columnIndex == this.firstColumn)
}