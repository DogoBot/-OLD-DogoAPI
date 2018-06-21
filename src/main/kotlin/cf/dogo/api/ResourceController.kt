package cf.dogo.api

import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController {
    @GetMapping("/guild/{id}")
    fun guild(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "guild").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/user/{id}")
    fun user(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "user").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/permgroup/{id}")
    fun permgroup(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "permgroup").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/stats")
    fun user(@RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "stats"))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/token/{id}/add")
    fun token1(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "addtoken").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/token/{id}/remove")
    fun token2(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "removetoken").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/token")
    fun token3(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "listtokens").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/api")
    fun api() = true
}