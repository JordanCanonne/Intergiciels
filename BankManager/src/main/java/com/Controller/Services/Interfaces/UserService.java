package com.Controller.Services.Interfaces;

import java.util.List;

import com.Model.Entities.CurrentAccount;
import com.Model.Entities.SavingAccount;
import com.Model.Entities.User;

public interface UserService {

	public void createUser(String userName, String password, CurrentAccount CurrentAccount, List<SavingAccount> listSavingAccount);
	public void createSampleList();
	public User findUser(String userName);
	public User findUserById(int id);
}
