package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufc.quixada.hackthonGreenMile.model.Team;
import br.ufc.quixada.hackthonGreenMile.repository.TeamRepository;
import br.ufc.quixada.hackthonGreenMile.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository repository;

	@Override
	public ResponseEntity<Team> create(Team team) {
		return new ResponseEntity<Team>(this.repository.save(team), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Team> get(Long id) {
		return new ResponseEntity<Team>(this.repository.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Team> update(Team team) {
		return new ResponseEntity<Team>(this.repository.save(team), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Team>> getAll() {
		return new ResponseEntity<List<Team>>(this.repository.findAll(), HttpStatus.OK);
	}

}
