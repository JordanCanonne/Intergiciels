package com.Model.Repository.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Model.Entities.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

	@Query("select a.role from UserRole a, User b where b.userName=?1 and a.id=b.id")
	public List<String> findRoleByUserName(String username);
}
