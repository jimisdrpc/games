package com.mycomp.jokenpo.controller

import com.mycomp.jokenpo.model.User
import com.mycomp.jokenpo.respository.UserRepository
import com.mycomp.jokenpo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import javax.validation.Valid

@RestController
@RequestMapping("users")
class UserController (private val userService: UserService, private val userRepository: UserRepository){

    val counter = AtomicLong()

//    @GetMapping("/user")
//    fun getUser(@RequestParam(value = "name", defaultValue = "World") name: String) =
//            User(counter.incrementAndGet(), "Hello, $name")

    @GetMapping()
    fun getAllUsers(): List<User> =
            userService.all()

    @PostMapping
    fun add(@Valid @RequestBody user: User): ResponseEntity<User> {
        //user.id?.let { userService.save(it) }
        val savedUser = userService.save(user)
        return ResponseEntity.ok(savedUser)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User> {
        return userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> {

        return userRepository.findById(userId).map { user  ->
            userRepository.deleteById(user.id)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

//    @DeleteMapping("{id}")
//    fun deleteUserById(@PathVariable id: Long): ResponseEntity<Unit> {
//        if (noteService.existsById(id)) {
//            noteService.deleteById(id)
//            return ResponseEntity.ok().build()
//        }
//        return ResponseEntity.notFound().build()
//    }

    /////

//    @PutMapping("{id}")
//    fun alter(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
//        return userRepository.findById(userId).map { user  ->
//            userRepository. deleteById(user.id)
//            ResponseEntity<Void>(HttpStatus.OK)
//        }.orElse(ResponseEntity.notFound().build())
//    }



}