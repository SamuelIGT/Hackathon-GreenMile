package br.ufc.quixada.hackthonGreenMile.security.jwt;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	//EXPIRATION_TIME = 10 days.
	static final long EXPIRATION_TIME = 864_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	//static Key key = MacProvider.generateKey();
	
	public static void addAuthentication(HttpServletResponse response, String username) {
		System.err.println("addAuthentication");
		String JWT = Jwts.builder()
					.setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECRET)
					.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null) {
			
			String user = Jwts.parser()
							.setSigningKey(SECRET)
							.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.getBody()
							.getSubject();
			
			if(user != null) {
				System.err.println("getAuthentication: user not null");
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<GrantedAuthority>());
			}
			System.err.println("getAuthentication: user null");
		}
		System.err.println("getAuthentication: token null");
		
		return null;
	}
}
