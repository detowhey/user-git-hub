package br.dev.henriquealmeida.usergithub.dto.response

data class UserRepositoryResponse(
    val name: String,
    val htmlUrl: String,
    val description: String?,
    val createdAt: String,
    val language: String?
)