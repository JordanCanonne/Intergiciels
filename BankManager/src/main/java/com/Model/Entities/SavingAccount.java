package com.Model.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement
public class SavingAccount {
	
	@Id
	@GeneratedValue
	private int id;
	private double solde;
	private double taux;
	private String name;
	@ManyToOne
	@JsonBackReference
	private User client;
	
	public SavingAccount() {
	}

	public SavingAccount(String name, double solde, double taux, User user) {
		super();
		this.solde = solde;
		this.taux = taux;
		this.client = user;
		this.setName(name);
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

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
