package br.ufc.quixada.hackthonGreenMile.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Member {	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String phone;
	
	@NotNull
	private String shirtSize;
	
	private String picture;
	
	@ManyToMany(mappedBy="members", cascade = CascadeType.ALL)
	private List<Team> teams;
	
	private Member() {}

	public Member(String name, String email, String phone, String shirtSize, String photo) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.shirtSize = shirtSize;
		this.picture = photo;
	}

}
