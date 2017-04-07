package com.Controller.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Controller.Services.Interfaces.CurrentAccountService;
import com.Controller.Services.Interfaces.SavingAccountService;
import com.Model.Entities.CurrentAccount;
import com.Model.Entities.SavingAccount;
import com.Model.Repository.Interfaces.SavingAccountRepository;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

	@Autowired
	SavingAccountRepository savingAccountRepository;
	@Autowired
	CurrentAccountService currentAccountService;

	@Transactional
	@Override
	public void credit(SavingAccount account, double montant) {
		savingAccountRepository.update(account, account.getSolde() + montant);
	}

	@Transactional
	@Override
	public boolean debit(SavingAccount account, double montant) {
		if (account.getSolde() - montant >= 0){
			savingAccountRepository.update(account, account.getSolde() - montant);
			return true;
		}
		return false;
	}

	@Override
	public SavingAccount findAccount(int id) {
		return savingAccountRepository.findOne(id);
	}
	
	@Transactional
	@Override
	public boolean virement(int from, String fromType, int to, String toType, double montant) {
		switch (fromType) {
		case "current":
			CurrentAccount fromAccount = currentAccountService.findAccount(from);
			SavingAccount toAccount = findAccount(to);
			if (currentAccountService.debit(fromAccount, montant)){
				credit(toAccount, montant);
				return true;
			}
			else
				return false;
		case "saving":
			SavingAccount fromAccount2 = findAccount(from);
			switch (toType) {
			case "current":
				CurrentAccount toAccount2 = currentAccountService.findAccount(to);
				if (debit(fromAccount2, montant)){
					currentAccountService.credit(toAccount2, montant);
					return true;
				}
				else
					return false;
			case "saving" :
				SavingAccount fromAccount3 = findAccount(from);
				if (debit(fromAccount2, montant)){
					credit(fromAccount3, montant);
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}
}
