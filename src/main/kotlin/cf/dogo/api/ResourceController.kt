package cf.dogo.api

import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

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

    @PostMapping("/token/")
    fun tokenPost(
            @RequestBody body : String,
            @RequestParam("token", required = false) token : String?
    ) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(
                JSONObject().put("token", token).put("iwant", "addtoken")
                        .put("body", JSONObject(body))

        )
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @DeleteMapping("/token/{id}")
    fun tokenDelete(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "removetoken").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/token")
    fun tokenGet(@RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "listtokens"))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/token/{id}")
    fun tokenGet(@PathVariable("id") id: String, @RequestParam("token", required = false) token : String?) : ResponseEntity<*>{
        val response = DogoAPI.connection?.request(JSONObject().put("token", token).put("iwant", "tokeninfo").put("id", id))
        return ResponseEntity(response.toString(), HttpStatus.OK)
    }

    @GetMapping("/api")
    fun api() = true
}