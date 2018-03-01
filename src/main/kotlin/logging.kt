import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.LoggerContext


fun main(args :Array<String>){
    System.setProperty("logDir", "log3")
    System.setProperty("logFile", "hogege")

    val loggerContext = LogManager.getContext(false) as LoggerContext
    val configuration = loggerContext.getConfiguration()
    val loggerConfig = configuration.getLoggerConfig(LogManager.ROOT_LOGGER_NAME)

    if(args.contains("-level=info")) {
        loggerConfig.level = Level.INFO
    } else {
        loggerConfig.level = Level.ERROR
    }


    loggerContext.updateLoggers()

    val logger = LogManager.getLogger("kotlin_playground")

    try {

        logger.info("info")
        logger.error("エラーが発生しました！")

        throw Exception("だめだkりゃ")

    } catch (e: Exception){
        var message = e.toString()
        for(st in e.stackTrace) {
            message = "$message\nat ${st}"
        }
       logger?.error(message)
    }



}