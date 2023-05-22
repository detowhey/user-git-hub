package br.dev.henriquealmeida.usergithub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(exclude = [SolrAutoConfiguration::class])
@EnableFeignClients
class UserProfileApplication

fun main(args: Array<String>) {
    runApplication<UserProfileApplication>(*args)
}
