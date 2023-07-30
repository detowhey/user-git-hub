package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.client.GitHubClient
import br.dev.henriquealmeida.usergithub.domain.UrlGitRepository
import br.dev.henriquealmeida.usergithub.domain.UserRepository
import br.dev.henriquealmeida.usergithub.exception.UserNotFoundException
import br.dev.henriquealmeida.usergithub.util.applyDateFormat
import br.dev.henriquealmeida.usergithub.util.parseToLocalDateTime
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserRepositoryService(@Autowired private val gitHubClient: GitHubClient) {

    private val logger = LoggerFactory.getLogger(UserRepositoryService::class.java)

    fun getListUserRepositories(userName: String, pageable: Pageable): Page<UserRepository> {
        try {
            val listRepository = gitHubClient.getRepositoriesGitHub(userName).map {
                UserRepository(
                    name = it.name,
                    htmlUrl = it.htmlUrl,
                    description = it.description,
                    createdAt = it.createdAt.applyDateFormat(),
                    language = it.language,
                    size = it.size,
                    urlGit = UrlGitRepository(git = it.gitUrl, ssh = it.sshUrl, clone = it.cloneUrl)
                )
            }

            val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, pageable.sort)
            val start = pageRequest.offset.toInt()
            val end = (start + pageRequest.pageSize).coerceAtMost(listRepository.size)
            var listOutput = listOf<UserRepository>()

            if (start <= end)
                listOutput = listRepository.subList(start, end)

            return PageImpl(listOutput, pageRequest, listRepository.size.toLong()).also {
                logger.info("Searching repositories, for user '$userName', in GitHub.")
            }
        } catch (exception: Exception) {
            logger.error("Service error with '$userName'", exception)
            throw UserNotFoundException(cause = exception.cause)
        }
    }
}
