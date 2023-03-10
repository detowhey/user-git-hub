package br.dev.henriquealmeida.usergithub.dto.response

import io.swagger.v3.oas.annotations.Hidden

@Hidden
data class UserExternalResponse(
    val login: String,
    val name: String,
    val avatar_url: String,
    val html_url: String,
    val created_at: String
)
