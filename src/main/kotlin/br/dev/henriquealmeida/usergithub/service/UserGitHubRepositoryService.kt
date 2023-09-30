package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.client.GitHubClient
import br.dev.henriquealmeida.usergithub.domain.UrlGitRepository
import br.dev.henriquealmeida.usergithub.domain.UserRepository
import br.dev.henriquealmeida.usergithub.exception.UserNotFoundException
import br.dev.henriquealmeida.usergithub.util.applyDateFormat
import br.dev.henriquealmeida.usergithub.util.parseToLocalDateTime
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserGitHubRepositoryService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserGitHubRepositoryService::class.java)

    fun getListUserRepositories(userName: String): List<UserRepository> {
        try {
            return gitHubClient.getRepositoriesGitHub(userName).map {
                UserRepository(
                    name = it.name,
                    htmlUrl = it.htmlUrl,
                    description = it.description,
                    createdAt = it.createdAt.applyDateFormat(),
                    language = it.language,
                    size = it.size,
                    urlGit = UrlGitRepository(git = it.gitUrl, ssh = it.sshUrl, clone = it.cloneUrl)
                )
            }.sortedByDescending {
                it.createdAt.parseToLocalDateTime()
            }.also {
                logger.info("Searching repositories, for user '$userName', in GitHub.")
            }
        } catch (exception: Exception) {
            logger.error("Service error with '$userName'\n${exception.message}", exception)
            throw UserNotFoundException(cause = exception.cause)
        }
    }
}
