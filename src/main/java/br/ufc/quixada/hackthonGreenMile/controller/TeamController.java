package br.ufc.quixada.hackthonGreenMile.controller;

import static br.ufc.quixada.hackthonGreenMile.util.Consts.API;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.TEAM;

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

import br.ufc.quixada.hackthonGreenMile.model.Team;
import br.ufc.quixada.hackthonGreenMile.service.TeamService;

@RestController
@RequestMapping(API + TEAM)
public class TeamController {

	@Autowired
	private TeamService service;
	
	@PostMapping
	public ResponseEntity<Team> create(@RequestBody Team team){
		
		return this.service.create(team);
	}

	@PutMapping
	public ResponseEntity<Team> update(@RequestBody Team team){
		
		return this.service.update(team);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Team> get(@PathVariable Long id){
		
		return this.service.get(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id){
		
		return this.service.delete(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Team>> getAll(){
		
		return this.service.getAll();
	}
	
	@PostMapping("/subscribe")
	public ResponseEntity<Team> get(@RequestBody Team team){
		
		return this.service.subscribe(team);
	}
	
	

}
