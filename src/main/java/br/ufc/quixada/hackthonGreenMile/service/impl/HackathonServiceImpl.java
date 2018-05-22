package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;
import br.ufc.quixada.hackthonGreenMile.model.Team;
import br.ufc.quixada.hackthonGreenMile.repository.HackathonRepository;
import br.ufc.quixada.hackthonGreenMile.repository.TeamRepository;
import br.ufc.quixada.hackthonGreenMile.service.HackathonService;

@Service
public class HackathonServiceImpl implements HackathonService {
	
	@Autowired
	private HackathonRepository repository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public ResponseEntity<Hackathon> create(Hackathon hackathon) {
		return new ResponseEntity<Hackathon>(this.repository.save(hackathon), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Hackathon> get(Long id) {
		return new ResponseEntity<Hackathon>(this.repository.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Hackathon> update(Hackathon hackathon) {
		return new ResponseEntity<Hackathon>(this.repository.save(hackathon), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Hackathon>> getAll() {
		return new ResponseEntity<List<Hackathon>>(this.repository.findAll(), HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<Page<Team>> getAllTeams(long hackathonid, int page, int size) {
        Page<Team> teams = teamRepository.findAllTeams(hackathonid, PageRequest.of(page, size));
		
        return new ResponseEntity<Page<Team>>(teams, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Team>> getAllTeamsOrderedByName(Long id, int page, int maxPageSize) {
		Page<Team> teams = teamRepository.findAllTeamsOrderedByName(id, PageRequest.of(page, maxPageSize));
		
        return new ResponseEntity<Page<Team>>(teams, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Team>> getAllTeamsOrderedByDate(Long id, int page, int maxPageSize) {
		Page<Team> teams = teamRepository.findAllTeamsOrderedByDate(id, PageRequest.of(page, maxPageSize));
		
        return new ResponseEntity<Page<Team>>(teams, HttpStatus.OK);
	}
}
