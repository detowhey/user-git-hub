package br.dev.henriquealmeida.usergithub.dto.response

import io.swagger.v3.oas.annotations.Hidden

@Hidden
data class UserRepositoryExternalResponse(
    val name: String,
    val html_url: String,
    val description: String?,
    val created_at: String,
    val git_url: String,
    val ssh_url: String,
    val clone_url: String,
    val size: Long,
    val language: String?,
)
