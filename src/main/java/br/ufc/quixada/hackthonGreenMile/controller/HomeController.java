package br.ufc.quixada.hackthonGreenMile.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
}
