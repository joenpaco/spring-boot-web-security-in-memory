package com.joenpaco.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Autowired
	public void ConfigurerGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		PasswordEncoder passwordEncoder = passwordEncoder();

		UserBuilder userBuilder = User.builder().passwordEncoder(passwordEncoder::encode);
			
			authenticationManagerBuilder.inMemoryAuthentication()
					.withUser(userBuilder.username("admin").password("123456").roles("ADMIN", "USER"))
					.withUser(userBuilder.username("jose").password("123456").roles("USER"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/listar", "/", "/css/**", "/js/**", "/images/**")
		.permitAll()
		.antMatchers("/form/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
			.logout()
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/error_403");
		
	}
	
	

}
