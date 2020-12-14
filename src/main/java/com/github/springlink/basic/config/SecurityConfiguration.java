package com.github.springlink.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.github.springlink.basic.core.TokenManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.oauth2ResourceServer()
			.opaqueToken();
		http.authorizeRequests()
			.antMatchers("/api/**").authenticated()
			.anyRequest().permitAll();
	}

	@Bean
	public TokenManager tokenManager() {
		return new TokenManager();
	}
}
