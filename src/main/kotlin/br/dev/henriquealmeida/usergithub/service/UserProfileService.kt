package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.client.GitHubClient
import br.dev.henriquealmeida.usergithub.domain.UserProfile
import br.dev.henriquealmeida.usergithub.exception.InvalidDateException
import br.dev.henriquealmeida.usergithub.exception.UserNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Service
class UserProfileService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserProfileService::class.java)

    fun getUserGitHub(userName: String): UserProfile {
        try {
            return gitHubClient.getUserGithub(userName).let {
                UserProfile(
                    it.login,
                    it.avatar_url,
                    it.name,
                    applyDateFormat(it.created_at),
                    it.html_url
                )
            }.also {
                logger.info("Searching user $userName in GitHub, $it")
            }
        } catch (exception: UserNotFoundException) {
          throw exception
        }
    }

    private fun applyDateFormat(dateValue: String, pattern: String = "dd/MM/yyyy HH:mm"): String {
        try {
            return OffsetDateTime.parse(dateValue).format(DateTimeFormatter.ofPattern(pattern))
        } catch (e: InvalidDateException) {
            throw InvalidDateException()
        }
    }
}
