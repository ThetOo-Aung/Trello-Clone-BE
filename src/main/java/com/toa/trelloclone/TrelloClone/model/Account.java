package com.toa.trelloclone.TrelloClone.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "account")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Account extends MainModel{
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Id
	private String username;
	private String name;
	private String email;
	
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	private short verified;
	
	public Account() {
		
	}
	@ManyToMany(mappedBy = "members")
	@JsonIgnoreProperties({"list", "members", "labels", "checklists"})
	private List<Card> cards;


	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public short getVerified() {
		return verified;
	}

	public void setVerified(short verified) {
		this.verified = verified;
	}
}
