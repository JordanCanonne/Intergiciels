package com.Model.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
public class CurrentAccount {
	
	@Id
	@GeneratedValue
	private int id;
	private double solde;
	private double decouvertAutorise;
	@ManyToOne
	@XmlTransient
	@JsonBackReference
	private User client;
	
	public CurrentAccount(){
		
	}
	
	public CurrentAccount(double solde, double decouvertAutorise, User client) {
		super();
		this.solde = solde;
		this.decouvertAutorise = decouvertAutorise;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public void setDecouvertAutorise(double decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}	

}
