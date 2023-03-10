package br.dev.henriquealmeida.usergithub.controller

import br.dev.henriquealmeida.usergithub.dto.response.UserProfileResponse
import br.dev.henriquealmeida.usergithub.service.UserProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["api/\${api.version}/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserProfileController(@Autowired private val userProfileService: UserProfileService) {

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
