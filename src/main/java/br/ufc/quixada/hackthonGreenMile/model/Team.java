package br.ufc.quixada.hackthonGreenMile.model;

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
public class Team {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	//@OneToMany(mappedBy = "team", targetEntity = Member.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "team_has_member", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = { @JoinColumn(name = "member_id")})
	private List<Member> members;
	
	@ManyToMany(mappedBy="teams")
	private List<Hackathon> hackathons;
	
	private Team() {
	}

	public Team(String name) {
		this.name = name;
	}
	
	

}
