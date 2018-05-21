package br.ufc.quixada.hackthonGreenMile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
				//.antMatchers("/h2/**/**").permitAll() //Allows all requests to the H2 database console url("/console/*")
				//.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
				
		http.headers().frameOptions().sameOrigin().cacheControl(); //required to set for h2 else h2 console will be blank
		
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
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/")
		.antMatchers("/h2/**/**");
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
