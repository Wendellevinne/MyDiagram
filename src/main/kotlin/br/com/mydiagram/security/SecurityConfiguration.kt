package br.com.mydiagram.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val jwtAuthFilter: JwtAuthenticationFilter, private val authenticationProvider: AuthenticationProvider) {

    val SwaggerI : String = "/v3/api-docs/**"
    val SwaggerII : String = "/v3/api-docs.yaml"
    val SwaggerIII : String = "/swagger-ui/**"
    val SwaggerIV : String = "/swagger-ui.html"

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
         http
             .csrf()
             .disable()
             .cors()
             .and()
             .authorizeHttpRequests()
             .requestMatchers("/api/v1/mydiagram/users/login")
             .permitAll()
             .requestMatchers("/api/v1/mydiagram/users/signup")
             .permitAll()
             .requestMatchers(SwaggerI, SwaggerII, SwaggerIII, SwaggerIV)
             .permitAll()
             .anyRequest()
             .authenticated()
             .and()
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
             .authenticationProvider(authenticationProvider)
             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}