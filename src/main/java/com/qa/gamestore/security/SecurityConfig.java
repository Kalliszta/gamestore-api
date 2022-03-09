package com.qa.gamestore.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.repo.AccountsRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private AccountsRepo repo;
	
//	@Autowired
//	public SecurityConfig(PasswordEncoder passwordEncoder, AccountsRepo repo) {
//		this.passwordEncoder = passwordEncoder;
//		this.repo = repo;
//	}
	
	@Autowired
	public SecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//TO-DO fix as /create can't be accessed
		//.antMatchers("**/create") //list of paths that don't require login
		//.permitAll()
		.antMatchers("http://localhost:8080/gamestore/accounts/read/all/").hasRole(UserRoles.ADMIN.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		//http.csrf().disable(); //disabled as when enabled restrict access to POST, PUT, DELETE
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
//		String username;
//		String password;
//		String userRole;
//		UserDetails user;
//		List<Accounts> all = this.repo.findAll();
		List<UserDetails> users = new ArrayList<>();
		
		UserDetails user1 = User.builder().username("user1").password(passwordEncoder.encode("pass1")).roles(UserRoles.ADMIN.name()).build(); //Spring reads roles as ROLE_CUSTOMER //TO-DO works with any password!?
		UserDetails user2 = User.builder().username("user2").password(passwordEncoder.encode("pass2")).roles(UserRoles.CUSTOMER.name()).build(); //Spring reads roles as ROLE_CUSTOMER //TO-DO works with any password!?
		
		users.add(user1);
		users.add(user2);
		
//		for (Accounts account: all) {
//			username = account.getUsername();
//			password = account.getPassword();
//			if (account.isAdmin()) {
//				userRole = UserRoles.ADMIN.name();
//			} else {
//				userRole = UserRoles.CUSTOMER.name();
//			}
//			user = User.builder().username(username).password(passwordEncoder.encode(password)).roles(userRole).build();
//			users.add(user);
//		}
		return new InMemoryUserDetailsManager(users);
	}
	
}