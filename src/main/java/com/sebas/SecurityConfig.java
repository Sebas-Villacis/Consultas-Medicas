package com.sebas;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bcrypt)
				.usersByUsernameQuery(
						"select * from (select nombre as username, clave as password, estado as enabled from usuario) as users where username = ?")
				.authoritiesByUsernameQuery(
						"select * from (select nombre as username, tipo as AUTHORITY from usuario) as authorities where username = ? ");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()		
		.antMatchers("/dashboard**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		.and().formLogin()
				.loginPage("/login").loginProcessingUrl("/dashboard").defaultSuccessUrl("/dashboard")
				.failureUrl("/login?error").usernameParameter("usuario").passwordParameter("clave").and().logout()
				.logoutSuccessUrl("/login?logout").logoutUrl("/j_spring_security_logout");
	}
	


}
