package com.Model.Repository.Interfaces;

import org.springframework.stereotype.Repository;

import com.Model.Entities.CurrentAccount;

@Repository
public interface CurrentAccountRepositoryCustom {
	public void update(CurrentAccount account,double montant);
}
