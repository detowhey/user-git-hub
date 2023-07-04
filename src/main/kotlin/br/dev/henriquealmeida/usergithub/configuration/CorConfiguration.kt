package br.dev.henriquealmeida.usergithub.configuration

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
class CorConfiguration {

    @Bean
    fun corsFilterRegistrationBean(): FilterRegistrationBean<CorsFilter>? {
        val config = CorsConfiguration().apply {
            this.allowCredentials = true
            this.allowedOriginPatterns = listOf("*")
            this.allowedMethods = listOf("*")
            this.allowedHeaders = listOf("*")
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            this.registerCorsConfiguration("/**", config)
        }

       val bean = FilterRegistrationBean<CorsFilter>().apply {
            this.filter = CorsFilter(source)
            this.order = Ordered.HIGHEST_PRECEDENCE
        }

        return bean
    }
}
