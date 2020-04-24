package com.mycomp.jokenpo.service

import com.mycomp.jokenpo.model.User
import com.mycomp.jokenpo.respository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserService(
        private val userRepository: UserRepository) {

    fun all(): List<User> {
        return userRepository.findAll().toList()
    }

    fun findById(id: Long) {
        userRepository.findById(id)
    }

    fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }

    fun existsById(id: Long): Boolean {
        return userRepository.existsById(id)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

//    fun alter(id: Long, user: User): User {
//        var safeUser = user.copy(id = id)
//        return save(safeUser)
//    }

}