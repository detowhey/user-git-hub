package br.dev.henriquealmeida.usergithub.service

import br.dev.henriquealmeida.usergithub.domain.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationService : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == "admin")
            return User("admin", "pass")
        else
            throw UsernameNotFoundException("User not found!")
    }
}