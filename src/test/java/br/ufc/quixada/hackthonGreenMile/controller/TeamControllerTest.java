package br.ufc.quixada.hackthonGreenMile.controller;

import static br.ufc.quixada.hackthonGreenMile.util.Consts.API;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.CREDENTIALS_DEFAULT;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.HACKATHON;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.LOCAL_HOST;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.LOGIN;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.TEAM;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import br.ufc.quixada.hackthonGreenMile.model.Member;
import br.ufc.quixada.hackthonGreenMile.model.Team;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

	public TeamControllerTest() {
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
		this.base = new URL(LOCAL_HOST + port + API + TEAM);
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
	public void canCreateTeam() throws Exception{ 
		HttpHeaders headers = getAutenticatedHeader();        
        
		Hackathon hackathon = new Hackathon("GreenMile", "Hackathon da GreenMile", "UFC Quixada", new Date(), 5, 10, true);
        HttpEntity<Hackathon> entity = new HttpEntity<>(hackathon, headers);
       
        ResponseEntity<Hackathon> response;
     
        response = template.exchange(LOCAL_HOST + port + API + HACKATHON, HttpMethod.POST, entity, Hackathon.class);

        System.err.println(base + "\n Status: " + response.getStatusCode());
        
        assertThat(response.getStatusCode().equals(HttpStatus.OK));
        
        hackathon = response.getBody();
        
        Member member1 = new Member("membro1", "membro1@teste.com", "8888", "MEDIO", null);
        Member member2 = new Member("membro2", "membro2@teste.com", "8888", "MEDIO", null);
        Member member3 = new Member("membro3", "membro3@teste.com", "8888", "MEDIO", null);
        Member member4 = new Member("membro4", "membro4@teste.com", "8888", "MEDIO", null);
        Member member5 = new Member("membro5", "membro5@teste.com", "8888", "MEDIO", null);
        
        Team team = new Team("Time 1");
        
        team.setHackathon(hackathon);
        team.setSubscriptionDate(new Date());
        
        
        List<Member> members = new ArrayList<>(); 

	}

}
