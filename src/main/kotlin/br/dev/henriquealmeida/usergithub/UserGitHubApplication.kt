package br.dev.henriquealmeida.usergithub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserGitHubApplication

fun main(args: Array<String>) {
	runApplication<UserGitHubApplication>(*args)
}
