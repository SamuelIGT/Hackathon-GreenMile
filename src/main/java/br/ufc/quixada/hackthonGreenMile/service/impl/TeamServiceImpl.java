package br.ufc.quixada.hackthonGreenMile.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufc.quixada.hackthonGreenMile.model.Hackathon;
import br.ufc.quixada.hackthonGreenMile.model.Member;
import br.ufc.quixada.hackthonGreenMile.model.Team;
import br.ufc.quixada.hackthonGreenMile.repository.HackathonRepository;
import br.ufc.quixada.hackthonGreenMile.repository.MemberRepository;
import br.ufc.quixada.hackthonGreenMile.repository.TeamRepository;
import br.ufc.quixada.hackthonGreenMile.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository repository;
	
	@Autowired
	private HackathonRepository hackathonRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public ResponseEntity<Team> create(Team team) {
		//team.setSubscriptionDate(new Date());//sets the current date
		
		Hackathon hackathon = hackathonRepository.findById(team.getHackathon().getId()).get();
		

		if(hackathon.isOpenForSubscription()) {		//If it is open for subscription
			for(Member member: team.getMembers()) {
				if(member.getTeams() == null) {
					member.setTeams(new ArrayList<>());
				}
				//verify if the member already belongs to this hackathon and if the team name already exist
				if(hackathon.alreadyExists(member.getId(), team.getName())) {
					return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
				}
				
				member.getTeams().add(team);
				memberRepository.save(member);
			}
			
			
			hackathon.getTeams().add(team);
			return new ResponseEntity<Team>(this.repository.save(team), HttpStatus.OK);
		}else {
			return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Boolean> delete(Team team) {
		team.setMembers(null); //removes everyone from this team
		repository.save(team);
		
		this.repository.deleteById(team.getId());
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
