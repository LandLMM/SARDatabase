package pl.sdacademy.sardatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService userDetailsService;


        public WebSecurityConfig(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/unauth/**", "/page/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();

        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("{noop}password1")
//                .roles("USER", "ABC")
//                .and()
//                .withUser("user2")
//                .password("{noop}password2")
//                .roles("ADMIN");
        // Będziemy używali UserDetailsService do pobierania danych do autentykacji (szczegółów użytkowników)
        auth.userDetailsService(userDetailsService);

        }

    public PasswordEncoder noopPaswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
