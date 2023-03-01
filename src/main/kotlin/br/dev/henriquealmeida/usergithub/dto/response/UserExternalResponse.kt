package br.dev.henriquealmeida.usergithub.dto.response

import io.swagger.v3.oas.annotations.Hidden

@Hidden
data class UserExternalResponse(
    val userName: String,
    val avatarUrl: String,
    val userProfileName: String,
    val startGitHubDate: String,
    val urlProfile: String
)
