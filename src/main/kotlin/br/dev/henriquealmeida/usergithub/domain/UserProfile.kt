package br.dev.henriquealmeida.usergithub.domain

data class UserProfile(
    val userName: String,
    val avatarUrl: String,
    val userProfileName: String,
    val startGitHubDate: String,
    val urlProfile: String
)
