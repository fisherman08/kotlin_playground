import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.LoggerContext


fun main(args :Array<String>){

    val props :HashMap<String, String> = hashMapOf(
            "logLevel" to "debug",
            "logDir" to "log",
            "logFile" to "lololo"
    )


    System.setProperty("logDir", props["logDir"] ?: "log")
    System.setProperty("logFile", props["logFile"] ?: "logging")
    System.setProperty("logFileExtension", props["logFileExtension"] ?: "log")

    val loggerContext = LogManager.getContext(false) as LoggerContext
    val configuration = loggerContext.getConfiguration()
    val loggerConfig = configuration.getLoggerConfig(LogManager.ROOT_LOGGER_NAME)

    when(props["logLevel"]) {
        "trace" -> loggerConfig.level = Level.TRACE
        "debug" -> loggerConfig.level = Level.DEBUG
        "info"  -> loggerConfig.level = Level.INFO
        "warn"  -> loggerConfig.level = Level.WARN
        "error" -> loggerConfig.level = Level.ERROR
        else    -> loggerConfig.level = Level.ERROR
    }

    loggerContext.updateLoggers()

    //val logger :Logger = LogManager.getLogger("kotlin_playground")
    val logger :Logger = LogManager.getLogger(Throwable().getStackTrace()[0].getClassName())


    try {

        doNothing()
        logger.debug("debug")
        logger.info("info")
        logger.error("エラーが発生しました！")

        throw Exception("だめだこりゃ")

    } catch (e: Exception){
        var message = e.toString()
        for(st in e.stackTrace) {
            message = "$message\nat ${st}"
        }
       logger?.error(message)
    }



}

fun doNothing() {

}