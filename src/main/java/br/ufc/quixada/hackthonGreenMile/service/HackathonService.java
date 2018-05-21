package br.ufc.quixada.hackthonGreenMile.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;
import br.ufc.quixada.hackthonGreenMile.model.Team;

public interface HackathonService {
	
	public ResponseEntity<Hackathon> create (Hackathon hackathon);
	
	public ResponseEntity<Boolean> delete (Long id);
	
	public ResponseEntity<Hackathon> get (Long id);
	
	public ResponseEntity<Hackathon> update (Hackathon hackathon);
	
	public ResponseEntity<List<Hackathon>> getAll ();
	
	public ResponseEntity<Page<Team>> getAllTeams(long hackathonid, int page, int size);

	public ResponseEntity<Page<Team>> getAllTeamsOrderedByName(Long id, int page, int maxPageSize);

	public ResponseEntity<Page<Team>> getAllTeamsOrderedByDate(Long id, int page, int maxPageSize);
	
//	public ResponseEntity<Boolean> unsubscribe (Team team);
}
