package br.dev.henriquealmeida.usergithub.domain

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Url", description = "The remote repository URLs",  accessMode = Schema.AccessMode.READ_ONLY)
data class UrlGitRepository(
    @field:Schema(description = "Git url", example = "git://github.com/example/project-examples.git")
    val git: String,
    @field:Schema(description = "SSH url", example = "git@/github.com:example/project-examples.git")
    val ssh: String,
    @field:Schema(description = "Git clone url", example = "https://github.com/example/project-examples.git")
    val clone: String
)
