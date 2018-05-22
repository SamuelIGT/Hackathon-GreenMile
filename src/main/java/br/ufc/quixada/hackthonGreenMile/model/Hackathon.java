package br.ufc.quixada.hackthonGreenMile.model;

import java.util.ArrayList;
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
	
	
	
	public boolean alreadyExists(Long memberId, String teamName) {
		for(Team team: this.teams) {
			//verify if the members already exist
			for(Member member: team.getMembers()) {
				if(member.getId() == memberId) {
					return true;
				}
			}
			//------------------
			//Verify if already exist a team with the same name
			if(teamName.equals(team.getName())){
				return true;
			}
		}
		return false;
	}


	public Hackathon(@NotNull String name, @NotNull String description, @NotNull String place, @NotNull Date date,
			@NotNull int numberOfMembersByTeam, @NotNull int numberOfTeams, @NotNull boolean isOpenForSubscription) {
		this.name = name;
		this.description = description;
		this.place = place;
		this.date = date;
		this.numberOfMembersByTeam = numberOfMembersByTeam;
		this.numberOfTeams = numberOfTeams;
		this.isOpenForSubscription = isOpenForSubscription;
	}
	
	

}
