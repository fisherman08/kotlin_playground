import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import java.io.*
import java.util.*

fun main(args: Array<String>){


    val os :OutputStream = ByteArrayOutputStream()
    val writer :Writer = OutputStreamWriter(os)
    val printer = CSVPrinter(writer, CSVFormat.DEFAULT)
    printer.print("ddd")
    printer.print("ee\"e\\")
    printer.print("fff")
    printer.println()
    printer.print("1")
    printer.print("2")
    printer.print("3")

    printer.flush()
    println(os.toString())
    printer.close()



    val inputStream :InputStream = ByteArrayInputStream(os.toString().toByteArray())
    val parser = CSVParser.parse(InputStreamReader(inputStream), CSVFormat.DEFAULT)
    val records = parser.records
    println(records[0][1])
    parser.close()


    val filepath = String.javaClass.getResource("/csv/test.csv").toURI()
    val fileparser = CSVParser.parse(File(filepath), charset("MS932"), CSVFormat.DEFAULT)
    val filerecords = fileparser.records
    fileparser.close()
    println(filerecords[0][1])
}

