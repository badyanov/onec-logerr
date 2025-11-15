package xs.badyanov.onec_logerr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(http -> http
                        // Статичные ресурсы и страница ошибок
                        .requestMatchers("/error", "/css/**", "/webjars/**", "/png/**", "/favicon.ico").permitAll()

                        // Ресурсы только для администратора
                        .requestMatchers("/users/**").hasAuthority("ROLE_ADMINISTRATOR")

                        // Главная страница и все остальные страницы
                        .requestMatchers("/**").authenticated()

                        // Полные права для администратора
                        .requestMatchers("/**").hasAuthority("ROLE_ADMINISTRATOR")

                        // Все остальные запросы
                        .anyRequest().denyAll())

                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/", false).permitAll())

                .logout(logout -> logout.permitAll()).build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
