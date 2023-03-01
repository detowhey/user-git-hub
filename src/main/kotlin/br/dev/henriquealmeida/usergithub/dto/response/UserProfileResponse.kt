package br.dev.henriquealmeida.usergithub.dto.response

data class UserProfileResponse(
    val userName: String,
    val avatarUrl: String,
    val userProfileName: String,
    val startGitHubDate: String,
    val urlProfile: String
)