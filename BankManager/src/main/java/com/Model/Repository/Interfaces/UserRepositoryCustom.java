package com.Model.Repository.Interfaces;

import org.springframework.stereotype.Repository;

import com.Model.Entities.CurrentAccount;
import com.Model.Entities.SavingAccount;
import com.Model.Entities.User;

@Repository
public interface UserRepositoryCustom {
	public void removeSavingAccount(User user, SavingAccount account);
	public void removeSavingAccount(int user_id, SavingAccount account);
	public void removeSavingAccount(int user_id, int account_id);
	
	public void addSavingAccount(User user, SavingAccount account);
	public void addSavingAccount(int user_id, SavingAccount account);
	
	public void initCurrentAccount(User user, CurrentAccount cAccount);
}
