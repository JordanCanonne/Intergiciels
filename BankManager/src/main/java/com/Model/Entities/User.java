package com.Model.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
@XmlRootElement
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	private String userName;
	@XmlTransient
	@JsonIgnore
	private String password;
	@XmlTransient
	@JsonIgnore
	private int enabled;
	@OneToOne(mappedBy="client", cascade = CascadeType.ALL)
	@JsonManagedReference
	private CurrentAccount CurrentAccount;
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL)
	@JsonManagedReference
	@XmlTransient
	@JsonIgnore
	private List<SavingAccount> listSavingAccount;
	
	public User(){
		
	}
	
	public User(User user){
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.enabled = user.getEnabled();
		this.CurrentAccount = user.getCurrentAccount();
		this.listSavingAccount = user.getListSavindAccount();
		
	}

	public User(String userName, String password, CurrentAccount CurrentAccount,
			List<SavingAccount> listSavingAccount) {
		super();
		this.userName = userName;
		this.password = password;
		this.enabled = 1;
		this.CurrentAccount = CurrentAccount;
		this.listSavingAccount = listSavingAccount;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getEnabled() {
		return enabled;
	}



	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CurrentAccount getCurrentAccount() {
		return CurrentAccount;
	}

	public void setCurrentAccount(CurrentAccount CurrentAccount) {
		this.CurrentAccount = CurrentAccount;
	}

	public List<SavingAccount> getListSavindAccount() {
		return listSavingAccount;
	}

	public void addSavingAccount(SavingAccount savindAccount) {
		if(this.listSavingAccount == null)
			this.listSavingAccount = new ArrayList<SavingAccount>();
		this.listSavingAccount.add(savindAccount);
	}
	
	public void removeSavingAccount(SavingAccount savingAccount){
		this.listSavingAccount.remove(savingAccount);
	}

}
