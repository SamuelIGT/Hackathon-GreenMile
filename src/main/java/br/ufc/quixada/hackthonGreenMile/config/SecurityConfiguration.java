package br.ufc.quixada.hackthonGreenMile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*-----------Allow h2 console---------*/
		http.authorizeRequests().antMatchers("/").permitAll().and() //Allow all requests to the root url ("/")
		.authorizeRequests().antMatchers("/h2/**").permitAll(); //Allow all requests to the H2 database console url("/console/*")
		System.out.println("TESTEEEEE!!!!!!!");
		http.csrf().disable(); //Disable CSRF protection
		http.headers().frameOptions().disable(); //Disable X-Frame-Options
		/*----------------END---------------*/
	}

}
