package com.mycomp.jokenpo.controller


import com.mycomp.jokenpo.model.User
import com.mycomp.jokenpo.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("games")
class GameController(private val userService: GameService) {

    @GetMapping("/playwithall")
    fun getGameWithAll(): User? = userService.playGameWithAll()


    @RequestMapping(value = ["/playwithsome/{ids}"], method = [RequestMethod.GET])
    @ResponseBody
    fun getGameWithSome(@PathVariable ids: Array<Long?>?): String? {
        return "Dummy"
    }
}