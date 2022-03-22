package com.example.bcpexchange.api.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(STATELESS);
        httpSecurity.authorizeHttpRequests().antMatchers("/api/login").permitAll();
        httpSecurity.authorizeHttpRequests().antMatchers(GET, "/api/users/**").hasAnyAuthority("USER_ROLE");
        httpSecurity.authorizeHttpRequests().antMatchers(GET, "/api/exchange-rate/**").hasAnyAuthority("USER_ROLE");
        httpSecurity.authorizeHttpRequests().antMatchers(POST, "/api/exchange-rate/**").hasAnyAuthority("ADMIN_ROLE");
        httpSecurity.authorizeHttpRequests().antMatchers(POST, "/api/transaction/**").hasAnyAuthority("USER_ROLE");
//        httpSecurity.authorizeHttpRequests().antMatchers(GET, "/api/users/**").hasAnyAuthority("ADMIN_ROLE");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.addFilter(customAuthenticationFilter);
        httpSecurity.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

