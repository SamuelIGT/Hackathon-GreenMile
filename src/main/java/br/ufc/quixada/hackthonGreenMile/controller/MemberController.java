package br.ufc.quixada.hackthonGreenMile.controller;

import static br.ufc.quixada.hackthonGreenMile.util.Consts.API;
import static br.ufc.quixada.hackthonGreenMile.util.Consts.MEMBER;

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

import br.ufc.quixada.hackthonGreenMile.model.Member;
import br.ufc.quixada.hackthonGreenMile.service.MemberService;

@RestController
@RequestMapping(API + MEMBER)
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping
	public ResponseEntity<Member> create(@RequestBody Member member){
		
		return this.service.create(member);
	}

	@PutMapping
	public ResponseEntity<Member> update(@RequestBody Member member){
		
		return this.service.update(member);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> get(@PathVariable Long id){
		
		return this.service.get(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id){
		
		return this.service.delete(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Member>> getAll(){
		
		return this.service.getAll();
	}

}
