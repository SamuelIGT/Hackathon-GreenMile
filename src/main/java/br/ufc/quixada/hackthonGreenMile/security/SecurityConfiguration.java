package br.ufc.quixada.hackthonGreenMile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import br.ufc.quixada.hackthonGreenMile.repository.AccountRepository;
import br.ufc.quixada.hackthonGreenMile.security.jwt.JWTAuthenticationFilter;
import br.ufc.quixada.hackthonGreenMile.security.jwt.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Component
	public class AccountDetailsService implements UserDetailsService{
		@Autowired
		private AccountRepository repository;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {			
			return this.repository.findByUsername(username);
		}
		
	}
	
	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//Disable CSRF protection
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic().and()
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
				/*.anyRequest()
					.authenticated()*/
			/*.and()
			.formLogin();*/
				
		
		/*http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.anyRequest().authenticated();*/
				
		
		/*-----------Allow h2 console---------*/
		/*http
		.authorizeRequests().antMatchers("/h2/**").permitAll(); //Allow all requests to the H2 database console url("/console/*")
		http.headers().frameOptions().disable(); //Disable X-Frame-Options*/
		/*----------------END---------------*/
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String key = encoder.encode("password");
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(key)
			.roles("ADMIN");
		
		auth.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder());
		
	}
	
/*	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
				User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}*/
	
	

}
