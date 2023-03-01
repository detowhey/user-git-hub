package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.client.GitHubClient
import br.dev.henriquealmeida.usergithub.domain.UserProfile
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserProfileService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserProfileService::class.java)

    fun getUserGitHub(nameUser: String): UserProfile {
        return gitHubClient.getUserGithub(nameUser).let {
            UserProfile(it.userName, it.avatarUrl, it.userProfileName, it.startGitHubDate, it.urlProfile)
        }.also {
            logger.info("Searching user in GitHub")
        }
    }
}
