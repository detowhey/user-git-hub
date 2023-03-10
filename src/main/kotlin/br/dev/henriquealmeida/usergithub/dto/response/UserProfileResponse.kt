package br.dev.henriquealmeida.usergithub.dto.response

data class UserProfileResponse(
    val login: String,
    val profileName: String,
    val avatarUrl: String,
    val urlProfile: String,
    val createDate: String
)
