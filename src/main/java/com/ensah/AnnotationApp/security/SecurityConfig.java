package com.ensah.AnnotationApp.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;
    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") //Toutes les URL commençant par /admin/ (ex: /admin/dashboard, /admin/users) sont accessibles uniquement aux utilisateurs ayant le rôle ADMIN. +
                        .requestMatchers("/annotateur/**").hasRole("USER")
                        .anyRequest().authenticated()  //Si un utilisateur essaie d'accéder à n'importe quelle URL , alors il doit être connecté (authentifié), sinon il sera redirigé vers la page de connexion (/login).
                )  // permet de définir les règles d'autorisation des requêtes HTTP  + Spring ajoute automatiquement le préfixe ROLE_
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception // Configure exception handling
                                .accessDeniedPage("/access-denied")
                )//seulement pour redirection vers une page personnalisée , l'exception existe par defaut
                .userDetailsService(customUserDetailsService);;

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
