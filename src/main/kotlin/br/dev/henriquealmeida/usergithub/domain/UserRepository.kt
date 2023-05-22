package br.dev.henriquealmeida.usergithub.domain

data class UserRepository(
    val name: String,
    val htmlUrl: String,
    val description: String?,
    val createdAt: String,
    val language: String?,
    val size: Long,
    val urlGit: UrlGitRepository
)
