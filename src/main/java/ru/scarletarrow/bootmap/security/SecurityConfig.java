package ru.scarletarrow.bootmap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.scarletarrow.bootmap.auth.AppUserService;

import java.util.concurrent.TimeUnit;

import static ru.scarletarrow.bootmap.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, AppUserService appUserService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//       http.csrf().disable().
//               authorizeRequests()
//               .antMatchers("/api/**")
//               .hasRole("ADMIN");
        http.
                csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
                and().
                authorizeRequests().
                antMatchers("/", "index").
                permitAll().
                antMatchers("/api/**").hasRole(ADMIN.name()).
                anyRequest().
                authenticated().and()
                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .passwordParameter("password")
//                .usernameParameter("username")
                .defaultSuccessUrl("/locations", true)
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(14))
                .key("something")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html        .clearAuthentication(true)
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me", "Idea-15b49c4a")
                .logoutSuccessUrl("/login");
//                    .loginPage("/login")
//                    .permitAll()
//                    .defaultSuccessUrl("/hola", true)
//                    .passwordParameter("password")
//                    .usernameParameter("username");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(appUserService);
        return daoAuthenticationProvider;
    }
}
