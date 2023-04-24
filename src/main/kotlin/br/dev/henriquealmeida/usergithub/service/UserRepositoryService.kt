package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.client.GitHubClient
import br.dev.henriquealmeida.usergithub.domain.UserRepository
import br.dev.henriquealmeida.usergithub.exception.InvalidDateException
import br.dev.henriquealmeida.usergithub.exception.UserNotFoundException
import br.dev.henriquealmeida.usergithub.util.applyDateFormat
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Service
class UserRepositoryService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserRepositoryService::class.java)

    fun getListUserRepositories(userName: String): List<UserRepository> {
        try {
            return gitHubClient.getRepositoriesGitHub(userName).map {
                it.let {
                    UserRepository(
                        it.name,
                        it.html_url,
                        it.description,
                        it.created_at.applyDateFormat(),
                        it.language
                    )
                }
            }.also {
                logger.info("Searching repositories, for user $userName, in GitHub.")
            }
        } catch (exception: UserNotFoundException) {
            throw exception
        }
    }
}
