package br.dev.henriquealmeida.usergithub.client

import br.dev.henriquealmeida.usergithub.dto.response.UserExternalResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "github-api", url = "\${github.api.url}")
interface GitHubClient {

    @GetMapping(value = ["/users/{userName}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserGithub(@PathVariable("userName") userName: String): UserExternalResponse
}