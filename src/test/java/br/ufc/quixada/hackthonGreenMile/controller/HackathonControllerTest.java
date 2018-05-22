package br.ufc.quixada.hackthonGreenMile.controller;

import static br.ufc.quixada.hackthonGreenMile.util.Consts.CREDENTIALS_DEFAULT;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.HACKATHON;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.LOCAL_HOST;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.LOGIN;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.API;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HackathonControllerTest {

	public HackathonControllerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@LocalServerPort
	private int port;
	
	private String token;
	//private HttpHeaders headers;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	//--------------------CONFIGURATIONS---------------------------------------|
	@Before
	public void setUp() throws Exception {
		getToken();
		//setHeaders();
		this.base = new URL(LOCAL_HOST + port + API + HACKATHON);
	}
	
	private void getToken() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add('Authorization', token);

        HttpEntity<String> entity = new HttpEntity<>(CREDENTIALS_DEFAULT, headers);
        ResponseEntity<String> response;
        try{
            response = template.postForEntity(LOCAL_HOST + port + LOGIN, entity, String.class);
            token = response.getHeaders().getValuesAsList("Authorization").get(0);
            System.out.println("Token: " + token + "\n STATUS: "+response.getStatusCode());
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	private HttpHeaders getAutenticatedHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		return headers;
	}
	
	//-----------------------------------------------------------------------------------------|
	//----------------------___TESTS___--------------------------------------------------------|
	
	@Test
	public void canCreateHackathon() throws Exception{ 
		HttpHeaders headers = getAutenticatedHeader();        
        
		Hackathon hackathon = new Hackathon("GreenMile", "Hackathon da GreenMile", "UFC Quixada", new Date(), 5, 10, true);
        HttpEntity<Hackathon> entity = new HttpEntity<>(hackathon, headers);
       
        ResponseEntity<Hackathon> response;
     
        response = template.exchange(base.toString(), HttpMethod.POST, entity, Hackathon.class);

        System.err.println(base + "\n Status: " + response.getStatusCode());
        
        assertNotNull(response.getBody());
        assertThat(response.getStatusCode().equals(HttpStatus.OK));
        
        assertThat(response.getBody().getName().equals(hackathon.getName()));
        assertThat(response.getBody().getDescription().equals(hackathon.getDescription()));
	}
	
	@Test
	public void canUpdateHackathon() throws Exception{ 
		HttpHeaders headers = getAutenticatedHeader();        
        
		Hackathon hackathon = new Hackathon("GreenMile", "Hackathon da GreenMile", "UFC Quixada", new Date(), 5, 10, true);
        HttpEntity<Hackathon> entity = new HttpEntity<>(hackathon, headers);
       
        ResponseEntity<Hackathon> response;
     
        response = template.exchange(base.toString(), HttpMethod.POST, entity, Hackathon.class);

        System.err.println(base + "\n Status: " + response.getStatusCode());
        hackathon = response.getBody();
        assertThat(hackathon.getName().equals(hackathon.getName()));
        
        String newName = "UFCQxd";
        Long id = hackathon.getId();
        
        hackathon.setName(newName);
        entity = new HttpEntity<>(hackathon, headers);
        response = template.exchange(base.toString(), HttpMethod.PUT, entity, Hackathon.class);
        hackathon = response.getBody();
        assertThat(hackathon.getId() == id);
        assertThat(hackathon.getName().equals(newName));
	}

}
