package com.Controller.Services.Interfaces;

import com.Model.Entities.CurrentAccount;

public interface CurrentAccountService {
	public void credit(CurrentAccount account, double montant);
	public boolean debit(CurrentAccount account, double montant);
	public boolean borrow(CurrentAccount account, double montant);
	public CurrentAccount findAccount(int id);
	public boolean virement(int from, int to, double montant);
}
