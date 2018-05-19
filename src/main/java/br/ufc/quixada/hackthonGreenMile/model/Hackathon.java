package br.ufc.quixada.hackthonGreenMile.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Hackathon {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;
	private String place;
	private Date date;
	private int numberOfMembersByTeam;
	private int numberOfTeams;
	
	@ManyToMany
	@JoinTable(name = "hackathon_has_team", joinColumns = {@JoinColumn(name = "hackathon_id")}, inverseJoinColumns = { @JoinColumn(name = "team_id")})
	private List<Team> teams;
	
	private Hackathon() {}

}
