package br.ufc.quixada.hackthonGreenMile.util;

import org.springframework.context.annotation.Configuration;


public class Consts {

	public static final String LOCAL_HOST = "http://localhost:";
	
	public static final String API = "/api";
	public static final String HACKATHON = "/hackathon";
	public static final String TEAM = "/team";
	public static final String MEMBER = "/member";
	public static final String LOGIN = "/login";
	
	public static final String CREDENTIALS_DEFAULT = "{\"username\":\"admin\", \"password\": \"password\"}";
	public static final int MAX_PAGE_SIZE = 10; 
	
	
	private Consts(){
		throw new AssertionError();
	}

}
