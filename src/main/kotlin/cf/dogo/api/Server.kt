package cf.dogo.api

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.ServerSocket
import java.net.Socket
import kotlin.collections.ArrayList

abstract class Server constructor(port : Int = 4676, name : String, logger : PrintStream = System.out) {
    val logger = logger
    val name = name;
    var socket = ServerSocket(port)
    var connections = ArrayList<Socket>()
    val connectionListener = ConnectionListener(this)
    val inputListener = InputListener(this)





    class ConnectionListener(srv : Server) : Thread("${srv.name} ConnectionListener") {
        val srv = srv;

        override fun run() {
            super.run()
            while(true) {
                var s = srv.socket.accept()
                if(!srv.connections.contains(s)){
                    srv.logger.println("[${this.name}] Connection Received! From ${s.remoteSocketAddress}")
                    srv.connections.add(s)
                }
                for(s2 in srv.connections){
                    if(s2.isClosed || !s2.isConnected) srv.connections.remove(s2)
                }
            }
        }
    }
    class InputListener(srv : Server) : Thread("${srv.name} InputListener") {
        val srv = srv;

        override fun run() {
            super.run()
            while(true){
                for(sck in srv.connections){
                    if(!sck.isClosed && sck.isConnected){
                        val scan = BufferedReader(InputStreamReader(sck.getInputStream()))
                        val content = scan.readLine()
                        if(content != null) {
                            val json = JSONObject(content)
                            val response = JSONObject()
                                    .put(
                                            "data",
                                            srv.onRequest(json.getInt("id"), json.getJSONObject("data"), sck)
                                    )
                                    .put("id", json.getInt("id"))
                            print(response)
                            sck.getOutputStream().write(json.toString().toByteArray())
                        }
                    }
                }
            }
        }
    }

    abstract fun onRequest(reqid : Int, data : JSONObject, sck : Socket) : JSONObject

    fun start(){
        connectionListener.start()
        inputListener.start()
    }

    fun stop(){
        connectionListener.stop()
        inputListener.stop()
        connections.forEach { c -> c.close() }
        connections.clear()
    }
}