package br.dev.henriquealmeida.usergithub.configuration

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.filter.CorsFilter

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
class CorsConfigurationGlobal {

    @Bean
    fun corsFilterRegistrationBean(): FilterRegistrationBean<CorsFilter>? {
        val config = CorsConfiguration().apply {
            this.allowCredentials = true
            this.allowedOriginPatterns = listOf(CorsConfiguration.ALL)
            this.allowedMethods = listOf(HttpMethod.GET.name)
            this.allowedHeaders = listOf("Requestor-Type")
            this.maxAge = 300L
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            this.registerCorsConfiguration("/**", config)
        }

        val corsBean = FilterRegistrationBean<CorsFilter>().apply {
            this.filter = CorsFilter(source)
        }

        return corsBean
    }

    @Bean
    fun characterEncodingFilterRegistration(): FilterRegistrationBean<CharacterEncodingFilter>? {
        val characterFilter = CharacterEncodingFilter().apply {
            this.encoding = "UTF-8"
            this.setForceEncoding(true)
        }

        val encodingBean = FilterRegistrationBean<CharacterEncodingFilter>().apply {
            this.filter = characterFilter
            this.addUrlPatterns("/*")
        }

        return encodingBean;
    }
}
