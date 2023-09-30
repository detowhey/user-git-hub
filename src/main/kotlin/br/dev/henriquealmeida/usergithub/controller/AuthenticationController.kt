package br.dev.henriquealmeida.usergithub.controller

import br.dev.henriquealmeida.usergithub.domain.User
import br.dev.henriquealmeida.usergithub.dto.request.AuthenticationRequest
import br.dev.henriquealmeida.usergithub.dto.response.LoginResponse
import br.dev.henriquealmeida.usergithub.service.AuthenticationService
import br.dev.henriquealmeida.usergithub.security.TokenService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["api/\${api.version}/auth"], produces = [MediaType.APPLICATION_JSON_VALUE])
class AuthenticationController(@Autowired private val tokenService: TokenService,
                               @Autowired private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid authenticationRequest: AuthenticationRequest): ResponseEntity<LoginResponse>  {
        val userNamePassword = UsernamePasswordAuthenticationToken(authenticationRequest.login,authenticationRequest.password)
        val auth = this.authenticationManager.authenticate(userNamePassword)
        val token = tokenService.generateToken(auth.principal as User)

        return ResponseEntity.ok(LoginResponse(token))
    }
}