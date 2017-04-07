package com.Model.Repository.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.Model.Entities.SavingAccount;
import com.Model.Repository.Interfaces.SavingAccountRepositoryCustom;

@Repository
public class SavingAccountRepositoryImpl implements SavingAccountRepositoryCustom {
	
	@PersistenceContext EntityManager em;

	public void update(SavingAccount account, double montant) {
		account.setSolde(montant);
		em.merge(account);
	}
	
}
