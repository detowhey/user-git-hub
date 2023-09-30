package br.dev.henriquealmeida.usergithub.security

import br.dev.henriquealmeida.usergithub.domain.User
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(@Autowired private val tokenService: TokenService) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = this.recoverToken(request)

        if (token != null) {
            val login = tokenService.validateToken(token)
            val user = User("login", "pass")
            val authentication = UsernamePasswordAuthenticationToken(user,null, user.authorities)
        }
        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")?.replace("Bearer ", "")
    }
}