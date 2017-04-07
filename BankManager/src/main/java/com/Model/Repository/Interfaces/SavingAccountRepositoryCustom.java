package com.Model.Repository.Interfaces;

import org.springframework.stereotype.Repository;

import com.Model.Entities.SavingAccount;

@Repository
public interface SavingAccountRepositoryCustom {
	public void update(SavingAccount account, double montant);
}
