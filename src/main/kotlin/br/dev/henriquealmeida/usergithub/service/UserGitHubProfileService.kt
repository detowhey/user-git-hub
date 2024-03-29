package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.client.GitHubClient
import br.dev.henriquealmeida.usergithub.domain.UserProfile
import br.dev.henriquealmeida.usergithub.exception.UserNotFoundException
import br.dev.henriquealmeida.usergithub.util.applyDateFormat
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserGitHubProfileService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserGitHubProfileService::class.java)

    fun getUserGitHub(userName: String): UserProfile {
        try {
            return gitHubClient.getUserGithub(userName).let {
                UserProfile(
                    it.login,
                    it.avatarUrl,
                    it.name,
                    it.createdAt.applyDateFormat(),
                    it.htmlUrl,
                    it.publicRepos
                )
            }.also {
                logger.info("Searching user $userName in GitHub.")
            }
        } catch (exception: Exception) {
            throw UserNotFoundException(cause = exception.cause)
        }
    }
}
