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
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Team {
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "team_has_member", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = { @JoinColumn(name = "member_id")})
	private List<Member> members;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Hackathon hackathon;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date subscriptionDate;
	
	private Team() {
	}

	public Team(String name) {
		this.name = name;
	}
	
	public boolean alreadyExists(Member member) {
		for(Member m: members) {
			if(member.getEmail().equals(m.getEmail())) {
				return false;
			}
		}
		return true;
	}
	
	

}
