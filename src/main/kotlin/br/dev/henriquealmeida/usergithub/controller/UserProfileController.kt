package br.dev.henriquealmeida.usergithub.controller

import br.dev.henriquealmeida.usergithub.dto.response.UserProfileResponse
import br.dev.henriquealmeida.usergithub.service.UserProfileService
import io.swagger.v3.oas.annotations.Operation
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

@Tag(name = "User profile", description = "Endpoint that integrates with the github public api")
@RestController
@RequestMapping(value = ["api/\${api.version}/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserProfileController(@Autowired private val userProfileService: UserProfileService) {

    @Operation(
        summary = "Search user profile on GitHub", responses = [ApiResponse(
            responseCode = "200",
            description = "Successfully found a user profile",
            content = [Content(schema = Schema(implementation = UserProfileResponse::class))]
        )]
    )
    @GetMapping(value = ["/{userName}"])
    fun getUserProfile(@PathVariable userName: String): ResponseEntity<UserProfileResponse> {
        return userProfileService.getUserGitHub(userName).let {
            ResponseEntity.ok().body(
                UserProfileResponse(
                    login = it.userName,
                    avatarUrl = it.avatarUrl,
                    profileName = it.userProfileName,
                    createDate = it.startGitHubDate,
                    urlProfile = it.urlProfile
                )
            )
        }
    }
}
