package br.dev.henriquealmeida.usergithub.dto.response

import br.dev.henriquealmeida.usergithub.domain.UrlGitRepository
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserRepository", description = "User repository properties")
data class UserRepositoryResponse(
    @field:Schema(description = "GitHub reposotiry name", example = "my-repository")
    val name: String,
    @field:Schema(description = "GitHub repository URL", example = "https://github.com/username/repository")
    val htmlUrl: String,
    @field:Schema(description = "GitHub repository description", example = "My repository example", nullable = true)
    val description: String?,
    @field:Schema(
        description = "GitHub repository created date",
        pattern = "dd/MM/yyyy HH:mm",
        example = "23/09/2020 16:51"
    )
    val createdAt: String,
    @field:Schema(description = "Language used in GitHub repository", example = "Java", nullable = true)
    val language: String?,
    @field:Schema(description = "Size of GitHub repository", example = "100")
    val size: Long,
    @field:JsonProperty(value = "url", access = JsonProperty.Access.READ_ONLY)
    @field:Schema(
        description = "GitHub URL repositories",
        accessMode = Schema.AccessMode.READ_ONLY,
        implementation = UrlGitRepository::class
    )
    val urlGit: UrlGitRepository
)