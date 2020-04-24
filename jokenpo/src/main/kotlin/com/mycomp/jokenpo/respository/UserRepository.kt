package com.mycomp.jokenpo.respository

import com.mycomp.jokenpo.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>