package br.ufc.quixada.hackthonGreenMile.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Member {	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String email;
	private String phone;
	private String shirtSize;
	private byte[] photo;
	
	@ManyToMany(mappedBy="members")
	private List<Team> teams;
	
	private Member() {}

	public Member(String name, String email, String phone, String shirtSize, byte[] photo) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.shirtSize = shirtSize;
		this.photo = photo;
	}
	
	

}
