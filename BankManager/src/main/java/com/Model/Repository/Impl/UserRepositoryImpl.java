package com.Model.Repository.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Model.Entities.CurrentAccount;
import com.Model.Entities.SavingAccount;
import com.Model.Entities.User;
import com.Model.Repository.Interfaces.SavingAccountRepository;
import com.Model.Repository.Interfaces.UserRepository;
import com.Model.Repository.Interfaces.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext	protected EntityManager em;
	
	private UserRepository userRepository;
	private SavingAccountRepository savingAccountRepository;

	
	public void removeSavingAccount(User user, SavingAccount account) {
		user.removeSavingAccount(account);
		em.merge(user);		
	}

	public void removeSavingAccount(int user_id, SavingAccount account) {
		User user = userRepository.findOne(user_id);
		removeSavingAccount(user, account);
	}

	public void removeSavingAccount(int user_id, int account_id) {
		User user = userRepository.findOne(user_id);
		SavingAccount account = savingAccountRepository.findOne(account_id);
		removeSavingAccount(user, account);
	}

	@Transactional
	public void addSavingAccount(User user, SavingAccount account) {
		user.addSavingAccount(account);
		em.merge(user);		
	}
	
	@Transactional
	public void addSavingAccount(int user_id, SavingAccount account) {
		User user = userRepository.findOne(user_id);
		addSavingAccount(user, account);
	}

	@Transactional
	public void initCurrentAccount(User user, CurrentAccount cAccount) {
		user.setCurrentAccount(cAccount);
		em.merge(user);		
	}
}
