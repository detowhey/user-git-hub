package br.dev.henriquealmeida.usergithub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class UserProfileApplication

fun main(args: Array<String>) {
	runApplication<UserProfileApplication>(*args)
}
