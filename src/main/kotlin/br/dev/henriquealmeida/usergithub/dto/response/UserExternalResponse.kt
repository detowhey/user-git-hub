package br.dev.henriquealmeida.usergithub.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.Hidden

@Hidden
data class UserExternalResponse(
    val login: String,
    val name: String,
    @field:JsonProperty("avatar_url")
    val avatarUrl: String,
    @field:JsonProperty("html_url")
    val htmlUrl: String,
    @field:JsonProperty("created_at")
    val createdAt: String,
    @field:JsonProperty("public_repos")
    val publicRepos: Long
)
