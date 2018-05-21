package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;
import br.ufc.quixada.hackthonGreenMile.model.Team;
import br.ufc.quixada.hackthonGreenMile.repository.HackathonRepository;
import br.ufc.quixada.hackthonGreenMile.service.HackathonService;

@Service
public class HackathonServiceImpl implements HackathonService {
	
	@Autowired
	private HackathonRepository repository;

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

	/*@Override
	public ResponseEntity<Boolean> unsubscribe(Team team) {
		return null;
	}*/

}
