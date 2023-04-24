package br.dev.henriquealmeida.usergithub.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserRepository", description = "List of user repositories")
data class UserRepositoryResponse(
    @field:Schema(description = "GitHub reposotiry name", example = "my-repository")
    val name: String,
    @field:Schema(description = "GitHub repository URL", example = "https://github.com/username/repository")
    val htmlUrl: String,
    @field:Schema(description = "GitHub repository descrition", example = "My repository example", nullable = true)
    val description: String?,
    @field:Schema(description = "GitHub repository created date", pattern = "dd/MM/yyyy HH:mm", example = "23/09/2020 16:51")
    val createdAt: String,
    @field:Schema(description = "Language used in GitHub repository", example = "Java", nullable = true)
    val language: String?
)