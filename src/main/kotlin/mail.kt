import org.apache.commons.mail.SimpleEmail
import javax.mail.internet.InternetAddress

fun main(args :Array<String>){

    try {
        val メアド = "example@example.com"
        val mail = SimpleEmail()

        mail.setHostName("localhost")
        //mail.setHostName("smtp.gmail.com")
        //mail.setSmtpPort(25)

       // mail.setSSLOnConnect(true)
        mail.setStartTLSEnabled(true)

        mail.setAuthentication("", "")

        mail.setFrom(メアド)
        mail.addTo(メアド)
        mail.setReplyTo(mutableListOf(InternetAddress("example@example.com")))
        mail.subject = "タイトルd"
        mail.setMsg("ほげげのげ")

        mail.send()

    } catch (e: Exception){
        e.printStackTrace()
    }



}