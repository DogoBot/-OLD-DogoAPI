package cf.dogo.api

import org.json.JSONObject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DogoAPI{
    companion object {
        var connection : Client? = null
    }
}

fun main(args : Array<String>){
    var ata = if(args.size > 1)  arrayOf(args[0], args[1]) else arrayOf("localhost", "4676")
    DogoAPI.connection = Client(ata[0], ata[1].toInt(), "Client")

    runApplication<DogoAPI>(*args)
}