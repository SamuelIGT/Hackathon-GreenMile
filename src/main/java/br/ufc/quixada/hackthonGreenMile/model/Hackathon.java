package br.ufc.quixada.hackthonGreenMile.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@NotNull
	private int numberOfMembersByTeam;
	
	@NotNull
	private int numberOfTeams;
	
	@NotNull
	private boolean isOpenForSubscription;
	
	@OneToMany
	@JsonIgnore
	private List<Team> teams;
	
	private Hackathon() {}
	

}
