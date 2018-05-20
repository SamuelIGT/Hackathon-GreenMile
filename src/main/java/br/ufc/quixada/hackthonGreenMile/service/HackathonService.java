package br.ufc.quixada.hackthonGreenMile.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;

public interface HackathonService {
	
	public ResponseEntity<Hackathon> create (Hackathon hackathon);
	
	public ResponseEntity<Boolean> delete (Long id);
	
	public ResponseEntity<Hackathon> get (Long id);
	
	public ResponseEntity<Hackathon> update (Hackathon hackathon);
	
	public ResponseEntity<List<Hackathon>> getAll ();
	}
