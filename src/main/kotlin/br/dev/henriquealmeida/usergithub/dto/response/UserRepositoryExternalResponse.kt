package br.dev.henriquealmeida.usergithub.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.Hidden

@Hidden
data class UserRepositoryExternalResponse(
        val name: String,
        @field:JsonProperty("html_url")
        val htmlUrl: String,
        val description: String?,
        @field:JsonProperty("created_at")
        val createdAt: String,
        @field:JsonProperty("git_url")
        val gitUrl: String,
        @field:JsonProperty("ssh_url")
        val sshUrl: String,
        @field:JsonProperty("clone_url")
        val cloneUrl: String,
        val size: Long,
        val language: String?,
)
