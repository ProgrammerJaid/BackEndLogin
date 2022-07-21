package com.myproject.securityproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.myproject.securityproject.AppUser.AppUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private AppUserService userDetailService;
	
	@Autowired
	private PasswordEncoder passEncode;

	@Autowired
	public SecurityConfig(AppUserService userDetailService) {
		this.userDetailService = userDetailService;
	}

	@Bean
	public SecurityFilterChain securedFilter(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
			.antMatchers("/api/v*/register/**").permitAll()
			.anyRequest()
			.authenticated()
			.and().formLogin()
			.defaultSuccessUrl("/welcome");

		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passEncode);
		provider.setUserDetailsService(userDetailService);

		return provider;
	}

}
