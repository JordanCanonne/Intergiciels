package com.Model.Repository.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.Model.Entities.CurrentAccount;
import com.Model.Repository.Interfaces.CurrentAccountRepositoryCustom;

@Repository
public class CurrentAccountRepositoryImpl implements CurrentAccountRepositoryCustom {
	
	@PersistenceContext private EntityManager em;

	public void update(CurrentAccount account, double montant) {
		account.setSolde(montant);
		em.merge(account);		
	}
	
}
