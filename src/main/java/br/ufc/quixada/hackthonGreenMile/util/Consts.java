package br.ufc.quixada.hackthonGreenMile.util;

import org.springframework.context.annotation.Configuration;


public class Consts {

	public static final String LOCAL_HOST = "http://localhost:";
	
	public static final String API = "/api";
	public static final String HACKATHON = "/hackathon";
	public static final String TEAM = "/team";
	public static final String MEMBER = "/member";
	
	//REQUEST METHODS
	/*public static final String GET = "/get";*/
	
	
	private Consts(){
		throw new AssertionError();
	}

}
