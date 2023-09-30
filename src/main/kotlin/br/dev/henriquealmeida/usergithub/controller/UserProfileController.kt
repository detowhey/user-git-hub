package br.dev.henriquealmeida.usergithub.controller

import br.dev.henriquealmeida.usergithub.dto.response.UserProfileResponse
import br.dev.henriquealmeida.usergithub.dto.response.UserRepositoryResponse
import br.dev.henriquealmeida.usergithub.service.UserGitHubProfileService
import br.dev.henriquealmeida.usergithub.service.UserGitHubRepositoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "User profile", description = "Endpoint that integrates with the github public API")
@RestController
@RequestMapping(value = ["api/\${api.version}/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserProfileController(
        @Autowired private val userGitHubProfileService: UserGitHubProfileService,
        @Autowired private val userGitHubRepositoryService: UserGitHubRepositoryService
) {

    @Operation(
        summary = "Search user profile on GitHub",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Successfully found a user profile",
            content = [Content(schema = Schema(implementation = UserProfileResponse::class))]
        )]
    )
    @GetMapping(value = ["/{userName}"])
    fun getUserProfile(@PathVariable userName: String): ResponseEntity<UserProfileResponse> {
        return userGitHubProfileService.getUserGitHub(userName).let {
            ResponseEntity.ok().body(
                UserProfileResponse(
                    login = it.userName,
                    avatarUrl = it.avatarUrl,
                    profileName = it.userProfileName,
                    createDate = it.startGitHubDate,
                    urlProfile = it.urlProfile,
                    numberPublicRepos = it.numberPublicRepos
                )
            )
        }
    }

    @Operation(
        summary = "Search repositories for user on GitHub",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Success finding the repositories",
            content = [Content(array = ArraySchema(schema = Schema(implementation = UserRepositoryResponse::class)))]
        )]
    )
    @GetMapping(value = ["/{userName}/repos"])
    fun getListUserRepositories(@PathVariable userName: String): ResponseEntity<List<UserRepositoryResponse>> {
        return userGitHubRepositoryService.getListUserRepositories(userName).map {
            UserRepositoryResponse(
                it.name,
                it.htmlUrl,
                it.description,
                it.createdAt,
                it.language,
                it.size,
                it.urlGit
            )
        }.let {
            ResponseEntity.ok(it)
        }
    }
}
