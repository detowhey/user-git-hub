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
class UserRepositoryService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserRepositoryService::class.java)

    fun getListUserRepositories(userName: String): List<UserRepository> {
        try {
            return gitHubClient.getRepositoriesGitHub(userName).map {
                it.let {
                    UserRepository(
                        it.name,
                        it.htmlUrl,
                        it.description,
                        it.createdAt.applyDateFormat(),
                        it.language,
                        it.size,
                        UrlGitRepository(it.gitUrl, it.sshUrl, it.cloneUrl)
                    )
                }
            }.sortedByDescending {
                it.createdAt.parseToLocalDateTime()
            }.also {
                logger.info("Searching repositories, for user $userName, in GitHub.")
            }
        } catch (exception: UserNotFoundException) {
            throw exception
        }
    }
}
