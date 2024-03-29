package br.dev.henriquealmeida.usergithub.client

import br.dev.henriquealmeida.usergithub.dto.response.UserExternalResponse
import br.dev.henriquealmeida.usergithub.dto.response.UserRepositoryExternalResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "github-api", url = "\${github.api.url}", dismiss404 = true)
interface GitHubClient {

    @GetMapping(value = ["/users/{userName}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserGithub(@PathVariable("userName") userName: String): UserExternalResponse

    @GetMapping(value = ["/users/{userName}/repos"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRepositoriesGitHub(@PathVariable("userName") userName: String): List<UserRepositoryExternalResponse>
}
