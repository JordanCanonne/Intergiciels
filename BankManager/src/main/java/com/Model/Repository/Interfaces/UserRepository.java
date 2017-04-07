package com.Model.Repository.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Model.Entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom{
	
	public User findByUserName(String username);
	public User findUserById(int id);
}
