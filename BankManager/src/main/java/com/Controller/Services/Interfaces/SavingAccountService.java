package com.Controller.Services.Interfaces;

import com.Model.Entities.SavingAccount;

public interface SavingAccountService {

	public boolean debit(SavingAccount account, double montant);
	public void credit(SavingAccount account, double montant);
	public  SavingAccount findAccount(int id);
	public boolean virement(int from, String fromType, int to, String toType, double montant);
	
}
