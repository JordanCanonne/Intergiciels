package com.Controller.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Controller.Services.Interfaces.CurrentAccountService;
import com.Model.Entities.CurrentAccount;
import com.Model.Repository.Interfaces.CurrentAccountRepository;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {
	
	@Autowired
	CurrentAccountRepository currentAccountRepository;
	
	@Transactional
	@Override
	public void credit(CurrentAccount account, double montant) {
		currentAccountRepository.update(account, account.getSolde() + montant);
	}

	@Transactional
	@Override
	public boolean debit(CurrentAccount account, double montant) {
		if (account.getSolde() + account.getDecouvertAutorise() - montant >= 0){
			currentAccountRepository.update(account, account.getSolde() - montant);
			return true;
		}
		return false;
	}
	
	@Transactional
	@Override
	public boolean borrow(CurrentAccount account, double montant){
		if (account.getSolde() >= 0){
			credit(account, montant);
			return true;
		}
		return false;
	}

	@Override
	public CurrentAccount findAccount(int id) {
		return currentAccountRepository.findOne(id);
	}

	@Transactional
	@Override
	public boolean virement(int from, int to, double montant) {
		CurrentAccount fromAccount = findAccount(from);
		CurrentAccount toAccount = findAccount(to);
		if (debit(fromAccount, montant)) {
			credit(toAccount, montant);
			return true;
		}
		return false;
	}
	
	
}
