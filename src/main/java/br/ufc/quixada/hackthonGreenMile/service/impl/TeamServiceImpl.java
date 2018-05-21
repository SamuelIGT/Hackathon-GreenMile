package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;
import br.ufc.quixada.hackthonGreenMile.model.Team;
import br.ufc.quixada.hackthonGreenMile.repository.HackathonRepository;
import br.ufc.quixada.hackthonGreenMile.repository.TeamRepository;
import br.ufc.quixada.hackthonGreenMile.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository repository;
	
	@Autowired
	private HackathonRepository hackathonRepository;

	@Override
	public ResponseEntity<Team> create(Team team) {
		//team.setSubscriptionDate(new Date());//sets the current date
		
		Hackathon hackathon = hackathonRepository.findById(team.getHackathon().getId()).get();
		
		//If it is open for subscription
		if(hackathon.isOpenForSubscription()) {
			hackathon.getTeams().add(team);
			return new ResponseEntity<Team>(this.repository.save(team), HttpStatus.OK);
		}else {
			return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
		}
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

	@Override
	public ResponseEntity<Team> subscribe(Team team) {
		Hackathon hackathon = hackathonRepository.findById(team.getHackathon().getId()).get();
		
		hackathon.getTeams().add(team);
		return null;
	}

}
