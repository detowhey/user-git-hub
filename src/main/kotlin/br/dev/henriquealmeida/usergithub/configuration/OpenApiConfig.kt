package br.dev.henriquealmeida.usergithub.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(@Value("\${api.version}") appVersion: String): OpenAPI {
        return OpenAPI().info(
                Info().title("GitHub user").description("Fetch GitHub user").version(appVersion)
            ).servers(
                listOf(
                    Server().url("http://localhost:8080").description("URL Service")
                )
            )
    }

    @Bean
    fun customGlobalResponses(): OpenApiCustomiser {
        return OpenApiCustomiser { openAPi ->
            openAPi.paths.values.forEach { pathItem ->
                pathItem.readOperations().forEach { operation ->
                    run {
                        operation.responses.let { apiResponse ->
                            apiResponse.addApiResponse("500", ApiResponse().description("Internal Server error"))
                            apiResponse.addApiResponse("404", ApiResponse().description("Resource not found"))
                        }
                    }
                }
            }
        }
    }
}
