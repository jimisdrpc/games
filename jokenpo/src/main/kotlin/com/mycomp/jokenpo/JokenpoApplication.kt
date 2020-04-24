package com.mycomp.jokenpo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JokenpoApplication

fun main(args: Array<String>) {
	runApplication<JokenpoApplication>(*args)
}
