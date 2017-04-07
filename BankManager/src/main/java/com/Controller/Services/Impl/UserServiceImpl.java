package com.Controller.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Controller.Services.Interfaces.UserService;
import com.Model.Entities.CurrentAccount;
import com.Model.Entities.SavingAccount;
import com.Model.Entities.User;
import com.Model.Repository.Interfaces.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Transactional
	public void createUser(String userName, String password,CurrentAccount CurrentAccount, List<SavingAccount> listSavingAccount){
		User user = new User(userName, password,CurrentAccount, listSavingAccount);
		user=userRepository.save(user);
		CurrentAccount cAccount = new CurrentAccount(300.00, 150.00, user);
		userRepository.initCurrentAccount(user, cAccount);
		SavingAccount sAccount = new SavingAccount("Livret A", 500, 3.5, user);
		userRepository.addSavingAccount(user, sAccount);
		SavingAccount sAccount2 = new SavingAccount("PEL", 1000, 4.00, user);
		userRepository.addSavingAccount(user, sAccount2);
	}
	
	public void createSampleList(){
		createUser("jordancanonne", "canonne", null, null);
		createUser("mikaeldesertot", "desertot", null, null);
	}
	
	@Transactional
	public User findUser(String userName){
		return userRepository.findByUserName(userName);
	}
	
	@Transactional
	public User findUserById(int id){
		return userRepository.findUserById(id);
	}
	
}
