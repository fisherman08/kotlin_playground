import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.LoggerContext
import org.apache.logging.log4j.core.config.LoggerConfig
import javax.security.auth.login.Configuration.getConfiguration



fun main(args :Array<String>){

    try {

        val loggerContext = LogManager.getContext(false) as LoggerContext
        val configuration = loggerContext.getConfiguration()
        val loggerConfig = configuration.getLoggerConfig(LogManager.ROOT_LOGGER_NAME)

        loggerConfig.setLevel(Level.INFO)
        loggerContext.updateLoggers()
        val logger = LogManager.getLogger("kotlin_playground")


        logger.info("info")
        logger.error("error")
    } catch (e: Exception){
        e.printStackTrace()
    }



}