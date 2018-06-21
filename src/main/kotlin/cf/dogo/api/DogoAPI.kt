package cf.dogo.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DogoAPI{
    companion object {
        var connection : Client? = null
    }
}

fun main(args : Array<String>) {
    var ata = if(args.size > 1)  arrayOf(args[0], args[1]) else arrayOf("localhost", "4676")
    DogoAPI.connection = Client(ata[0], ata[1].toInt(), "Client")

    //yep, that == true is required
    if(DogoAPI.connection?.isAvailabe() == true) {
        runApplication<DogoAPI>(*args)
        } else {
        Thread.sleep(30000)
        main(args)
    }
}