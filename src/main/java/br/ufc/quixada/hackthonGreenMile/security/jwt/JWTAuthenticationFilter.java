package br.ufc.quixada.hackthonGreenMile.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.err.println("DoFilter");
		/*System.err.println("Path"+ ((HttpServletRequest)request).getServletPath());
		
		String path = ((HttpServletRequest)request).getServletPath();
		
		if(path.length() > 3) {
			path = path.substring(0, 4);
		}*/
		
		
		//if("/api".equals(path)) {
		Authentication authentication = TokenAuthenticationService
										.getAuthentication((HttpServletRequest)request);
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		//}
	}
}
