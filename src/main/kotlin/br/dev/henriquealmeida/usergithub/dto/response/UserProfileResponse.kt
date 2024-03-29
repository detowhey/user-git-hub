package br.dev.henriquealmeida.usergithub.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserProfile", description = "User data")
data class UserProfileResponse(
    @field:Schema(description = "GitHub user login", example = "username")
    val login: String,
    @field:Schema(description = "GitHub user profile name", example = "Username Example")
    val profileName: String,
    @field:Schema(description = "GitHub user avatar URL", example = "https://avatars.githubusercontent.com/example")
    val avatarUrl: String,
    @field:Schema(description = "GitHub user profile URL", example = "https://github.com/example")
    val urlProfile: String,
    @field:Schema(description = "GitHub user create date profile", pattern = "dd/MM/yyyy HH:mm", example = "10/02/2020 14:21")
    val createDate: String,
    @field:Schema(description = "GitHub user number of public repositories", example = "5", type = "Number")
    @field:JsonProperty("amountRepositories")
    val numberPublicRepos: Long
)
