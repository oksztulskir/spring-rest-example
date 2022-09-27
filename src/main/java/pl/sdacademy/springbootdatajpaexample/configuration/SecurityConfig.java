package pl.sdacademy.springbootdatajpaexample.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceAdapter userDetailsServiceAdapter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/mvc/user/**").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceAdapter);
//        auth.inMemoryAuthentication()
//                .withUser("user").password("$2a$10$Oz1ah60KxsTXRtn4XLJHmOlypqk6qBhLyjRjG6VcLuonHZz4q99UG").roles("USER")
//                .and()
//                .withUser("admin").password("$2a$10$ttruqGcv28dOz/VPUuRi.uBrCHIkM9oPFs1uWh.XtmLqg5XllmLA.").roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
