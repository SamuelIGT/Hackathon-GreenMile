package br.ufc.quixada.hackthonGreenMile.controller;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.API;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.HACKATHON;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;
import br.ufc.quixada.hackthonGreenMile.service.HackathonService;

@RestController
@RequestMapping(API + HACKATHON)
public class HackathonController {
	
	@Autowired
	private HackathonService service;
	
	@PostMapping
	public ResponseEntity<Hackathon> create(@RequestBody Hackathon hackathon){
		
		return this.service.create(hackathon);
	}

	@PutMapping
	public ResponseEntity<Hackathon> update(@RequestBody Hackathon hackathon){
		
		return this.service.update(hackathon);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hackathon> get(@PathVariable Long id){
		
		return this.service.get(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> create(@PathVariable Long id){
		
		return this.service.delete(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Hackathon>> getAll(){
		
		return this.service.getAll();
	}
}