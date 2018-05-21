package br.ufc.quixada.hackthonGreenMile.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Hackathon {

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private String place;
	
	@NotNull
	private Date date;
	
	@NotNull
	private int numberOfMembersByTeam;
	
	@NotNull
	private int numberOfTeams;
	
	@NotNull
	private boolean isOpenForSubscription;
	
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "hackathon_has_team", joinColumns = {@JoinColumn(name = "hackathon_id")}, inverseJoinColumns = { @JoinColumn(name = "team_id")})
	private List<Team> teams;
	
	private Hackathon() {}
	

}
